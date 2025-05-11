pipeline{
	agent any
  environment {
	   DISCORD_WEBHOOK_URL = "https://discord.com/api/webhooks/1367422757712822285/n_Tu4iYef4cD0SQlFYvoHMwPcCHK0Ef7XEpmXUkRYmG0uDliJUxyFWzYPLGm_DSlU2fN"
      BRANCH_MASTER = 'master'
  }
stages {
	stage('Configure Release IDs') {
            steps {
                bat './configure_release_ids.bat'
            }
        }
 	stage('Build') {
            
            steps {
                bat '''
                    chmod +x gradlew
                    ./gradlew clean app:assembleDebug
                '''
            }
        }
	stage('Archive') {
        steps {
            archiveArtifacts '**/*.apk'
        }
    }
	stage('Discord Notification') {

            steps {
                discordSend(
                    description: "",
                    unstable: true,
		    link: env.BUILD_URL,
                result: "${currentBuild.currentResult}",
                title: "${JOB_NAME}",
                    webhookURL: env.DISCORD_WEBHOOK_URL
                )
	        }
        }
	
}
}
