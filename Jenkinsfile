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
                archiveArtifacts artifacts: 'target/**/*.*', fingerprint: true
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

//                         emailext (
//                             subject: "Job '${env.JOB_NAME} ${env.BUILD_NUMBER}'",
//                             body: """<p>Check console output at <a href="${env.BUILD_URL}">${env.JOB_NAME}</a></p>""",
//                             to: "report@code-maven.com",
//                             from: "jenkins@code-maven.com"
//                         )

//                         step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: "olegivanov1989@gmail.com", sendToIndividuals: true])
                    }
                }
            }
        }
    }
 }

 def sendNotifications() {
    def summary = junit testResults: '**/target/surefire-reports/*.xml'

    def branch = bat(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD\n').trim().tokenize().last()
    def emailMessage = "${currentBuild.currentResult}: Job '${env.JOB_NAME}', Build ${env.BUILD_NUMBER}, Branch ${branch}. \nPassed time: ${currentBuild.durationString}. \n\nTESTS:\nTotal = ${summary.totalCount},\nFailures = ${summary.failCount},\nSkipped = ${summary.skipCount},\nPassed = ${summary.passCount} \n\nMore info at: ${env.BUILD_URL}"

    emailext (
        subject: "Jenkins Report",
        body: emailMessage,
        to: "${EMAIL_TO}",
        from: "jenkins@code-maven.com"
    )

    def colorCode = '#FF0000'
    def slackMessage = "${currentBuild.currentResult}: Job '${env.JOB_NAME}', Build ${env.BUILD_NUMBER}. \nTotal = ${summary.totalCount}, Failures = ${summary.failCount}, Skipped = ${summary.skipCount}, Passed = ${summary.passCount} \nMore info at: ${env.BUILD_URL}"

    slackSend(color: colorCode, message: slackMessage)
 }