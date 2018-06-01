#/bin/bash
#ident "%W%"

usage()
{
  echo "******"
  exit 1
}

if ["$1" = ""]; then
  usage
fi

JAR="DEMO.jar"
export JAVA_HOME=/jdk/1.6
export RUN_ARGS=" -Denv=${1} -Dspring.profiles.active=${1}"
export VM_OPS=" -Xms${}JAVA_MIN}m -Xmx${JAVA_MAX}m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${DUMP_DIR}/OOM_${FILE_NAME}_${TIME} -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:${GC_LOG} -Dcom.sun.management.jmxremote.port=${JMX_PORT} -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false "

pushd /var

${JAVA_HOME}/bin/java -jar ${RUN_ARGS} ${JAR} >> ${CONSOLE_LOG} 2>$1

popd
