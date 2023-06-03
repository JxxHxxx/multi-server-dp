pipeline {
    agent any

    environment {
            API_SERVER_PEM_KEY = credentials('mt-dp-pem')
            API_REMOTE_SERVER_IP = '54.180.116.61'
    }

    tools {
        gradle '7.6.1'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning Repository'

                git url: 'https://github.com/JxxHxxx/multi-server-dp.git',
                    branch: 'main',
                    credentialsId: 'abbfa91f-b62a-4b27-bed8-3300e7cd4e27'
            }
        }

        stage('Build') {
            steps {
                echo 'Build'
                dir('/var/lib/jenkins/workspace/multi-server-dp-practice') {
                    sh 'gradle build'
                }
            }
        }

        stage('Deploy') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'mt-dp-pem', keyFileVariable: 'PEM_KEY')]) {
                    dir('/var/lib/jenkins/workspace/multi-server-dp-practice/build/libs') {
                        sh "scp -o StrictHostKeyChecking=no -i $PEM_KEY multi-server-dp-0.0.1-SNAPSHOT.jar ubuntu@$API_REMOTE_SERVER_IP:/home/ubuntu"
                        sh "ssh -o StrictHostKeyChecking=no -i $PEM_KEY ubuntu@$API_REMOTE_SERVER_IP chmod +x deploy.sh"
                        sh "ssh -o StrictHostKeyChecking=no -i $PEM_KEY ubuntu@$API_REMOTE_SERVER_IP ./deploy.sh"
                    }
                }
            }
        }
    }
}