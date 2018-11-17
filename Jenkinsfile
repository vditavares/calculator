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
            steps {
               sh "mvn clean compile"
            }
        }
        stage("Package") {
            steps {
               sh "mvn package -DskipTests"
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
 		stage("Docker build") {
			steps {
				sh "docker build -t localhost:5000/calculator ."
			}
		}  
		stage("Docker push") {
			steps {
				sh "docker push localhost:5000/calculator"
			}
		}	
		stage("Deploy to staging") {
			steps {
				sh "docker run -d --rm -p 8765:8080 --name calculator localhost:5000/calculator"
			}
		}	        
    }
   	post { 
   		always  {
   			sh "docker stop calculator"
   		}
   	}
}