node {
    def app
    def registry = "diegoromero60/rest-calculator"
    def registryCredential = 'docker-hub-credentials'
    def dockerImage = ''
    def HOST_DB_CALCULATOR = "192.168.0.5:27017"

    stage('Clone repository') {
        checkout scm
    }

    stage('Build Repository') {
        sh "mvn clean package"
    }
    
    stage('Build image') {
        app = docker.build registry + ":$BUILD_NUMBER"
    }

    stage('Push image') {
        docker.withRegistry('', 'docker-hub-credentials') {
            app.push()
        }
    }
    
    stage('Run locally after build image'){
        sh "docker run --env HOST=$HOST_DB_CALCULATOR $registry:$BUILD_NUMBER &"
    }    
}