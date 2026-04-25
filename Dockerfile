# Stage 1: Extract layered architecture (Builder Stage)
FROM eclipse-temurin:17-jre-alpine AS builder
WORKDIR /app
COPY target/*.jar app.jar
# Extract layers from the fat jar using Spring Boot's layertools
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 2: Production execution (Runtime Stage)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 1. Security & Permissions: OpenShift SCC Compliance
# OpenShift runs containers with a random UID, but always in the root (0) group.
# We create a non-root user and ensure the app directory is group-writable.
RUN addgroup -S appgroup && adduser -S appuser -G appgroup \
    && chown -R appuser:root /app \
    && chmod -R g=u /app
# Explicitly switch to the non-root user
USER appuser

# 2. Layered Copy: Maximize Docker caching efficiency
# Copy from least frequently changed (dependencies) to most frequently changed (application code)
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

# 3. Environment Variables & JVM Tuning
ENV TZ="Asia/Shanghai"
# Restrict JVM heap to 75% of container limit, leaving 25% for OS and off-heap memory to prevent OOMKilled
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=75.0 -XX:+UseG1GC -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080

# 4. Optimized Startup
# Use Spring Boot's JarLauncher instead of 'java -jar' for faster startup of layered jars
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]