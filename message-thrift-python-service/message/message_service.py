import smtplib
from email.header import Header
from email.mime.text import MIMEText

from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
from thrift.transport import TSocket
from thrift.transport import TTransport

from message.api import MessageService

sender = 'your-email@xx.com'
authCode = 'email authCode'


class MessageServiceHandler:
    def sendMobileMessage(self, mobile, message):
        print("send mobile message")
        print(mobile)
        print(message)
        return True

    def sendEmailMessage(self, email, message):
        print("send email message")

        messageObject = MIMEText(message, "plain", "utf-8")
        messageObject['From'] = sender
        messageObject['To'] = email
        messageObject['Subject'] = Header('email subject', 'utf-8')

        try:
            smtpObject = smtplib.SMTP('smpt.xxx.com')
            smtpObject.login(sender, authCode)
            smtpObject.sendmail(sender, [email], messageObject.as_string())

        except smtplib.SMTPException as arguments:
            print("send email failed")
            print(arguments)
            return False
        print("send email success")

        return True


if __name__ == '__main__':
    handler = MessageServiceHandler()
    processor = MessageService.Processor(handler)

    transport = TSocket.TServerSocket(host="127.0.0.1", port=9090)

    tFactory = TTransport.TFramedTransportFactory()

    pFactory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TSimpleServer(processor, transport, tFactory, pFactory)

    print("python thrift message service start")
    server.serve()
    print("python thrift message service exit")
