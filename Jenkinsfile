pipeline {
    agent any
    
    environment {
        DOCKERHUB_USER = "princekarma"
        IMAGE_NAME = "swe645-hw3"
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
        
        stage('Build Application') {
            steps {
                script {
                    echo 'Building Spring Boot application...'
                    // Clean and build the application
                    sh './mvnw clean package -DskipTests'
                    
                    // Verify JAR file was created
                    sh 'ls -la target/*.jar'
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker image...'
                    dockerImage = docker.build("${DOCKERHUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}")
                    dockerImage.tag("${env.BUILD_NUMBER}")
                    dockerImage.tag("build-${env.BUILD_NUMBER}")
                }
            }
        }
        
        stage('Push to Docker Hub') {
            steps {
                script {
                    echo 'Pushing Docker image to Docker Hub...'
                    docker.withRegistry('https://registry.hub.docker.com', 'DockerCreds') {
                        dockerImage.push("${IMAGE_TAG}")
                        dockerImage.push("${env.BUILD_NUMBER}")
                        dockerImage.push("build-${env.BUILD_NUMBER}")
                    }
                }
            }
        }
        
        stage('Update Kubernetes Deployment') {
            steps {
                script {
                    echo 'Deploying to Kubernetes...'
                    withCredentials([file(credentialsId: 'KubeCreds', variable: 'KUBECONFIG')]) {
                        // Update the deployment YAML with the new image tag
                        sh """
                            sed -i 's|${DOCKERHUB_USER}/${IMAGE_NAME}:latest|${DOCKERHUB_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER}|g' ${DEPLOYMENT_YAML}
                        """
                        
                        // Apply Kubernetes configurations
                        sh "kubectl apply -f ${DEPLOYMENT_YAML}"
                        sh "kubectl apply -f ${SERVICE_YAML}"
                        
                        // Wait for deployment to complete
                        sh "kubectl rollout status deployment/swe645-hw3-survey --timeout=300s"
                        
                        // Verify pods are running
                        sh "kubectl get pods -l app=swe645-hw3-survey"
                        
                        // Get service information
                        sh "kubectl get service swe645-hw3-survey-service"
                    }
                }
            }
        }
    
    post {
        success {
            echo 'Pipeline completed successfully!'
            echo "Spring Boot application built and tested"
            echo "Docker image ${DOCKERHUB_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER} pushed to Docker Hub"
            echo "Application deployed to Kubernetes cluster"
            echo "Service available on NodePort 30080"
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
                    sh "docker rmi ${DOCKERHUB_USER}/${IMAGE_NAME}:${IMAGE_TAG} || true"
                    sh "docker rmi ${DOCKERHUB_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER} || true"
                    sh "docker rmi ${DOCKERHUB_USER}/${IMAGE_NAME}:build-${env.BUILD_NUMBER} || true"
                    
                    // Clean up any dangling images
                    sh "docker image prune -f || true"

                } catch (Exception e) {
                    echo "Cleanup failed: ${e.getMessage()}"
                }
            }
        }
    }
}