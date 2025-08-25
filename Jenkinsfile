pipeline {
    agent any
    
    environment {
        dockerimagename = "geoffrey0pv/backend-trademe-ecommerce"
        dockerImage = ""
        registryCredential = 'dockerhub-credentials'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/TradeMe-Ecommerce/Backend-Repository.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'echo "Skipping tests in pipeline"'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${dockerimagename}:${BUILD_NUMBER}")
                }
            }
        }

        stage('Push DockerHub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
                        dockerImage.push("${BUILD_NUMBER}")
                        
                        dockerImage.push("latest")
                    }
                }
            }
        }
        
        stage('Cleanup') {
            steps {
                script {
                    sh "docker rmi ${dockerimagename}:${BUILD_NUMBER} || true"
                    sh "docker rmi ${dockerimagename}:latest || true"
                }
            }
        }
    }
    
    post {
        always {
            sh 'docker system prune -f || true'
        }
        success {
            echo "Pipeline ejecutado exitosamente!"
            echo "Imagen disponible en DockerHub: ${dockerimagename}:${BUILD_NUMBER}"
            echo "También disponible como: ${dockerimagename}:latest"
        }
        failure {
            echo "Pipeline falló. Revisa los logs para más detalles."
        }
        cleanup {
            cleanWs()
        }
    }
}