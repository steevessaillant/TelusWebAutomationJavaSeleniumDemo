pipeline {
    agent any

    environment {
        MAVEN_HOME = tool name: 'Maven', type: 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    def isUnix = isUnix()

                    sh script: "${tool name: 'Java', type: 'jdk'}/bin/java -version"
                    sh script: "${MAVEN_HOME}/bin/mvn clean install"
                }
            }
        }

        stage('Cucumber Test Report') {
            steps {
                script {
                    def junitResults = junit testResults: '**/target/surefire-reports/TEST-*.xml'
                    cucumber 'cucumber-reports', jsonReportDirectory: 'target/cucumber-reports'
                }
            }
        }
    }

    post {
        failure {
            emailext attachmentsPattern: '**/target/surefire-reports/*.txt',
                body: 'Build failed. See attached test reports for details.',
                subject: 'Build Failed',
                to: 'your-email@example.com'
        }

        unstable {
            emailext attachmentsPattern: '**/target/surefire-reports/*.txt',
                body: 'Build unstable. See attached test reports for details.',
                subject: 'Build Unstable',
                to: 'your-email@example.com'
        }
    }
}
