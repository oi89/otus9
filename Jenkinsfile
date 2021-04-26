pipeline {
    agent any

    tools {
            maven 'MAVEN'
    }

    environment {
        LC_ALL = 'en_US.UTF-8'
        LANG    = 'en_US.UTF-8'
        LANGUAGE = 'en_US.UTF-8'
        EMAIL_TO = 'olegivanov1989@gmail.com'
    }

    parameters {
            string(name: 'GIT_URL', defaultValue: 'https://github.com/oi89/otus9.git', description: 'The target git url')
            string(name: 'GIT_BRANCH', defaultValue: 'master', description: 'The target git branch')
    }

    stages {
        stage('Pull from GitHub') {
            steps {
                git ([
                    url: "${params.GIT_URL}",
                    branch: "${params.GIT_BRANCH}"
                    ])
            }
        }
        stage('Run maven clean test') {
            steps {
                bat 'mvn clean test -Dfile.encoding=UTF8'
            }
        }
        stage('Backup and Reports') {
            steps {
                archiveArtifacts artifacts: '**/target/', fingerprint: true
            }
            post {
                always {
                    script {
                        allure([
                            includeProperties: false,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'target/allure-results']]
                        ])

                        sendNotifications()

                        emailext (body: 'Test body',
                        to: "${EMAIL_TO}",
                        subject: 'Test subject')

//                         emailext (
//                             subject: "Job '${env.JOB_NAME} ${env.BUILD_NUMBER}'",
//                             body: """<p>Check console output at <a href="${env.BUILD_URL}">${env.JOB_NAME}</a></p>""",
//                             to: "report@code-maven.com",
//                             from: "jenkins@code-maven.com"
//                         )

                        step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: "olegivanov1989@gmail.com", sendToIndividuals: true])
                    }
                }
            }
        }
    }
 }

 def sendNotifications() {
    def summary = junit testResults: '**/target/surefire-reports/*.xml'

    def colorCode = '#FF0000'
    def message = "${currentBuild.currentResult}: Job '${env.JOB_NAME}', Build ${env.BUILD_NUMBER}. \n Total = ${summary.totalCount}, Failures = ${summary.failCount}, Skipped = ${summary.skipCount}, Passed = ${summary.passCount}"

//     emailext (
//         subject: "Jenkins report",
//         body: message,
//         to: "olegivanov1989@gmail.com",
//         from: "jenkins@code-maven.com"
//     )
    slackSend(color: colorCode, message: message)
 }