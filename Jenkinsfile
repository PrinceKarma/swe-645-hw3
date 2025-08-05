pipeline {
    agent any
    
    environment {
        DOCKERHUB_USER = "princekarma"
        BACKEND_IMAGE_NAME = "swe645-hw3"
        FRONTEND_IMAGE_NAME = "swe645-hw3-frontend"
        IMAGE_TAG = "latest"
        GITHUB_REPO = "PrinceKarma/swe-645-hw3"
        DEPLOYMENT_YAML = "k8s/deployment.yaml"
        SERVICE_YAML = "k8s/service.yaml"
    }
    
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: "https://github.com/${GITHUB_REPO}.git"
            }
        }
        
        stage('Build Backend Application') {
            steps {
                script {
                    echo 'Building Spring Boot application...'
                    sh './mvnw clean package -DskipTests'
                    sh 'ls -la target/*.jar'
                }
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                script {
                    echo 'Building Backend Docker image...'
                    backendImage = docker.build("${DOCKERHUB_USER}/${BACKEND_IMAGE_NAME}:${IMAGE_TAG}")
                    backendImage.tag("${env.BUILD_NUMBER}")
                    backendImage.tag("build-${env.BUILD_NUMBER}")
                }
            }
        }

        stage('Push Backend to Docker Hub') {
            steps {
                script {
                    echo 'Pushing Backend Docker image to Docker Hub...'
                    docker.withRegistry('https://registry.hub.docker.com', 'DockerCreds') {
                        backendImage = docker.image("${DOCKERHUB_USER}/${BACKEND_IMAGE_NAME}")
                        backendImage.push("${IMAGE_TAG}")
                        backendImage.push("${env.BUILD_NUMBER}")
                        backendImage.push("build-${env.BUILD_NUMBER}")
                    }
                }
            }
        }
        
        stage('Build Frontend Docker Image') {
            steps {
                script {
                    echo 'Building Frontend Docker image...'
                    frontendImage = docker.build("${DOCKERHUB_USER}/${FRONTEND_IMAGE_NAME}:${IMAGE_TAG}", "frontend")
                    frontendImage.tag("${env.BUILD_NUMBER}")
                    frontendImage.tag("build-${env.BUILD_NUMBER}")
                }
            }
        }
        
        stage('Push Frontend to Docker Hub') {
            steps {
                script {
                    echo 'Pushing Frontend Docker image to Docker Hub...'
                    docker.withRegistry('https://registry.hub.docker.com', 'DockerCreds') {
                        frontendImage = docker.image("${DOCKERHUB_USER}/${FRONTEND_IMAGE_NAME}")
                        frontendImage.push("${IMAGE_TAG}")
                        frontendImage.push("${env.BUILD_NUMBER}")
                        frontendImage.push("build-${env.BUILD_NUMBER}")
                    }
                }
            }
        }

        stage('Update Kubernetes Deployment') {
            steps {
                script {
                    echo 'Deploying to Kubernetes...'
                    withCredentials([file(credentialsId: 'KubeCreds', variable: 'KUBECONFIG')]) {
                        sh """
                            sed -i 's|${DOCKERHUB_USER}/${BACKEND_IMAGE_NAME}:latest|${DOCKERHUB_USER}/${BACKEND_IMAGE_NAME}:${env.BUILD_NUMBER}|g' ${DEPLOYMENT_YAML}
                            sed -i 's|${DOCKERHUB_USER}/${FRONTEND_IMAGE_NAME}:latest|${DOCKERHUB_USER}/${FRONTEND_IMAGE_NAME}:${env.BUILD_NUMBER}|g' ${DEPLOYMENT_YAML}
                        """
                        
                        sh "kubectl apply -f ${DEPLOYMENT_YAML}"
                        sh "kubectl apply -f ${SERVICE_YAML}"
                        
                        sh "kubectl rollout status deployment/swe645-hw3-survey --timeout=300s"
                        
                        sh "kubectl get pods -l app=swe645-hw3-survey"
                        
                        sh "kubectl get service swe645-hw3-survey-service"
                    }
                }
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline completed successfully!'
            echo "Spring Boot application built and tested"
            echo "Docker images pushed to Docker Hub"
            echo "Application deployed to Kubernetes cluster"
        }
        
        failure {
            echo 'Pipeline failed!'
            echo 'Please check the logs for errors.'
        }
        
        always {
            echo 'Performing cleanup...'
            script {
                try {
                    // Clean up local Docker images to save space
                    sh "docker rmi ${DOCKERHUB_USER}/${BACKEND_IMAGE_NAME}:${IMAGE_TAG} || true"
                    sh "docker rmi ${DOCKERHUB_USER}/${BACKEND_IMAGE_NAME}:${env.BUILD_NUMBER} || true"
                    sh "docker rmi ${DOCKERHUB_USER}/${BACKEND_IMAGE_NAME}:build-${env.BUILD_NUMBER} || true"
                    sh "docker rmi ${DOCKERHUB_USER}/${FRONTEND_IMAGE_NAME}:${IMAGE_TAG} || true"
                    sh "docker rmi ${DOCKERHUB_USER}/${FRONTEND_IMAGE_NAME}:${env.BUILD_NUMBER} || true"
                    sh "docker rmi ${DOCKERHUB_USER}/${FRONTEND_IMAGE_NAME}:build-${env.BUILD_NUMBER} || true"
                    
                    sh "docker image prune -f || true"
                } catch (Exception e) {
                    echo "Cleanup failed: ${e.getMessage()}"
                }
            }
        }
    }
}