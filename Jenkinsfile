pipeline {
    agent { label 'windows' }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvn clean test'
            }
            post {
                always {
                    junit testResults: 'course-registration-app/target/surefire-reports/TEST-*.xml',
                          allowEmptyResults: false
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'course-registration-app/target/*.jar',
                             fingerprint: true
        }
    }
}
