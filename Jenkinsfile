pipeline {
	agent any
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
			agent {
		        docker {
		            image 'maven:3-alpine'
		            args '-v /root/.m2:/root/.m2'
		        }
		    }
            steps {
               sh "mvn clean compile"
            }
        }
        stage("Package") {
 			agent {
		        docker {
		            image 'maven:3-alpine'
		            args '-v /root/.m2:/root/.m2'
		        }
		    }           
            steps {
               sh "mvn package -DskipTests"
            }
        }
        stage("Docker build") {
			sh "docker build -t localhost:5000/calculator ."
		}  
		stage("Docker push") {
			steps {
				sh "docker push localhost:5000/calculator"
			}
		}		      
        stage("Unit Test") {
			agent {
		        docker {
		            image 'maven:3-alpine'
		            args '-v /root/.m2:/root/.m2'
		        }
		    }            
            steps {
               sh "mvn clean test"
               junit 'target/surefire-reports/*.xml'
            }
        }
        stage("Code coverage") {
			agent {
		        docker {
		            image 'maven:3-alpine'
		            args '-v /root/.m2:/root/.m2'
		        }
		    }			
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
			agent {
		        docker {
		            image 'maven:3-alpine'
		            args '-v /root/.m2:/root/.m2'
		        }
		    }        	
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