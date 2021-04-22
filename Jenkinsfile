pipeline {
    agent any

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
                bat 'mvn clean test'
            }
        }
        stage('Backup and Reports') {
            steps {
                echo 'Test stage'
            }
        }
    }
 }