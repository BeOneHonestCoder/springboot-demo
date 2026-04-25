pipeline {
    agent any

    environment {
        // 1. Fetch OpenShift Token from Jenkins Credentials
        OS_TOKEN = credentials('OPENSHIFT_TOKEN')

        // 2. Fetch Docker Hub Credentials
        // Jenkins automatically creates: DOCKER_CREDS_USR (Username) and DOCKER_CREDS_PSW (Token)
        DOCKER_CREDS = credentials('DOCKER_HUB_LOGIN')

        // 3. Project Configuration
        OS_SERVER = 'https://api.rm2.thpm.p1.openshiftapps.com:6443'
        NAMESPACE = 'tobedeveloperken-dev'
        APP_NAME  = 'springdemo-app'

        // Use Jenkins build number for unique image tagging
        IMAGE_TAG = "v${env.BUILD_NUMBER}"
    }

    stages {
        stage('Source Checkout') {
            steps {
                echo 'Pulling source code from Git...'
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                echo 'Building application artifact...'
                // Skip tests for faster demo; use ./mvnw if wrapper is present
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Image Operations') {
            steps {
                script {
                    // Combine credentials username with app name for the full image path
                    def registryImage = "${DOCKER_CREDS_USR}/${APP_NAME}"

                    echo "Building Docker image: ${registryImage}:${IMAGE_TAG}"
                    sh "docker build -t ${registryImage}:${IMAGE_TAG} ."
                    sh "docker build -t ${registryImage}:latest ."

                    echo 'Logging into Docker Hub...'
                    // Secure login using stdin to avoid exposing token in logs
                    sh "echo \$DOCKER_CREDS_PSW | docker login -u \$DOCKER_CREDS_USR --password-stdin"

                    echo 'Pushing images to Docker Hub...'
                    sh "docker push ${registryImage}:${IMAGE_TAG}"
                    sh "docker push ${registryImage}:latest"
                }
            }
        }

        stage('OpenShift Deployment') {
            steps {
                echo 'Authenticating with OpenShift API...'
                sh "oc login --token=${OS_TOKEN} --server=${OS_SERVER} --insecure-skip-tls-verify"
                sh "oc project ${NAMESPACE}"

                echo 'Executing Helm Upgrade...'
                // Dynamically inject image repository and tag into Helm Chart
                sh """
                    helm upgrade --install ${APP_NAME} ./charts/${APP_NAME} \
                    --set image.repository=${DOCKER_CREDS_USR}/${APP_NAME} \
                    --set image.tag=${IMAGE_TAG} \
                    --namespace ${NAMESPACE}
                """
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Pipeline failed! Check the logs for troubleshooting.'
        }
        always {
            echo 'Performing cleanup...'
            // Ensure session is cleared even if the build fails
            sh 'docker logout'
        }
    }
}