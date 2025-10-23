pipeline {
    agent any
    environment {
                PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"

                // Define Docker Hub credentials ID
                DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
                // Define Docker Hub repository name
                DOCKERHUB_REPO = 'gerlih/shopping-cart-test'
                // Define Docker image tag
                DOCKER_IMAGE_TAG = 'latest'
            }

    tools {
            maven 'MAVEN'
        }
    stages {
        stage('checkout') {
            steps {
                git branch:'master', url:'https://github.com/CrimsonBambi/ShoppingCartTest.git'
            }
        }
        stage('build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                recordCoverage(tools: [[parser: 'JACOCO']],
                        id: 'jacoco', name: 'JaCoCo Coverage',
                        sourceCodeRetention: 'EVERY_BUILD',
                        qualityGates: [
                                [threshold: 60.0, metric: 'LINE', baseline: 'PROJECT', unstable: true],
                                [threshold: 60.0, metric: 'BRANCH', baseline: 'PROJECT', unstable: true]])
            }
        }
        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG% .'
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat '''
                        docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                        docker push %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG%
                    '''
                }
            }
        }
    }
}