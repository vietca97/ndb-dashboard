pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git branch: "master", url: 'https://github.com/vietca97/nbd-dashboard.git'
            }
        }
        stage('Build') {

            steps {
                script {
                     def pom = readMavenPom file: 'pom.xml'
                     version = pom.version
                     name = pom.name
                    sh 'mvn clean verify install'
                    sh 'mvn dockerfile:build'
                    sh "docker-compose up -d --build"
                }
            }
        }
    }
}
