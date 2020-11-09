import org.example.Constants
def call(Map config=[:]) {
    if (config.type == "slack") {
        echo Constants.SLACK_MESSAGE
        echo config.message
    } else {
        echo Constants.EMAIL_MESSAGE
        echo config.message
		mail bcc: '', body: '''Hello Madhu,''', cc: '', from: '', replyTo: '', subject: 'Jenkins Shared Library Notification', to: 'madhu.spmm@gmail.com'
    }
}
