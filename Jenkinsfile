pipeline {
	agent any
	tools {
        maven 'Maven 3.8.8'
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

