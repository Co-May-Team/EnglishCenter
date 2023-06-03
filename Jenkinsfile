pipeline {
	agent any
	environment {
        // Define the MAVEN_HOME environment variable
        MAVEN_HOME = tool name: 'Maven 3.8.8', type: 'maven'
        // Add Maven bin directory to the PATH environment variable
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Build') { 
            steps {
				sh 'mvn clean install'
            }
        } 
		stage('Deploy') { 
            steps {
				sh 'sudo systemctl enable englishcenter.service'
				sh 'sudo systemctl stop englishcenter'
				sh 'sudo systemctl start englishcenter'
				sh 'sudo systemctl status englishcenter'
            }
        }		
	}
}

