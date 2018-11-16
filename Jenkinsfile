pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    triggers {
		pollSCM('* * * * *')
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
              sh " mvn clean clover:instrument clover:clover"
             
               sh "mvn clean clover:instrument clover:check"
               
            }
        }
    }
    post {
        always {
            junit '/target/surefire-reports/*.xml'
        }
    }
}