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
               sh "mvn clean compile"
            }
        }
        stage("Package") {
            steps {
               sh "mvn package -DskipTests"
            }
        }
        stage("Docker build") {
			agent { label 'my-defined-label' }
			steps {
				script {
				    def apitestimage = docker.build("calculator")
				}
			}
		}        
        stage("Unit Test") {
            steps {
               sh "mvn clean test"
               junit 'target/surefire-reports/*.xml'
            }
        }
        stage("Code coverage") {
			steps {
				//sh "mvn clean clover:instrument clover:clover"
				///publishHTML (target: [
				//		reportDir: 'target/site/clover/',
				//		reportFiles: 'index.html',
				//		reportName: "Clover Report"
				//])
				sh "mvn clover:check -Dmaven.clover.targetPercentage=50% -Dmaven.clover.failOnViolation=true"
			}
		}
        stage("Static code analysis") {
        	steps {
            	sh "mvn clean checkstyle:checkstyle"
              	publishHTML (target: [
					reportDir: 'target/site/',
					reportFiles: 'checkstyle.html',
					reportName: "Checkstyle Report"
				])
            }
        }
    }
   	
}