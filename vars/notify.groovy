import org.example.Constants
def call(Map config=[:]) {
    if (config.type == "slack") {
        echo Constants.SLACK_MESSAGE
        echo config.message
	    /*slackSend channel: '#jenkins', message: config.message*/
	  slackSend channel: '#jenkins',
          color: 'good',
          message: "*${currentBuild.currentResult}:* ${config.message} *${env.JOB_NAME}* build no: ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
    } else {
        echo Constants.EMAIL_MESSAGE
	emailext body: config.message, subject: 'Build Status', to: 'madhu.spmm@gmail.com'
        echo config.message
	/*mail bcc: '', body: '''Hello Madhu,''', cc: '', from: '', replyTo: '', subject: 'Jenkins Shared Library Notification', to: 'madhu.spmm@gmail.com'*/
	emailext attachLog: true, 
		 body: "*${currentBuild.currentResult}:* ${config.message} *${env.JOB_NAME}* build no: ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}", 
		 recipientProviders: [buildUser()], 
		 subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}", 
		 to: 'madhu.spmm@gmail.com'
		   
    }
}
