pipeline{
	agent any
  environment {
	   DISCORD_WEBHOOK_URL = "https://discord.com/api/webhooks/1367422757712822285/n_Tu4iYef4cD0SQlFYvoHMwPcCHK0Ef7XEpmXUkRYmG0uDliJUxyFWzYPLGm_DSlU2fN"
      BRANCH_MASTER = 'master'
  }
stages {
 	stage('Build') {
            
            steps {
                bat '''
                    chmod +x gradlew
                    ./gradlew clean
                    ./gradlew app:assembleAppDev
                    ./gradlew app:bundleRelease
                '''
            }
        }
	stage('Deploy Artifact') {
          
    		steps {
                	bat"""
		 copy  -r app/build/outputs/apk
		 """
    		}
	}
}
}
