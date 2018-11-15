pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage("Checkout") {
            steps {
                git url: 'https://github.com/vditavares/calculator.git'
            }
        }
        stage("Compile") {
            steps {
               sh "mvn compile"
            }
        }
         stage("Unit Test") {
            steps {
               sh "mvn clean test"
            }
        }
        stage("Code coverage") {
            steps {
               sh "checkstyle:checkstyle"
            }
        }
    }
}