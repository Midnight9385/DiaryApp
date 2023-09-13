package EmailSystem;

// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import de.milchreis.uibooster.components.Notification;

import java.io.IOException;

public class EmailPasswordSend {
    final static String emailServerPassword = "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";
    final static Email from = new Email("diaryappnoreply@gmail.com");
    final static String subject = "Sending with SendGrid is Fun";
  public static void main(String[] args) throws IOException {
    
    Email to = new Email("zacharynewkirk88@gmail.com");
    Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(emailServerPassword);
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      throw ex;
    }
  }

  public static void sendEmail(String email, String password, String username){
    // System.out.println("starting send method");
    Email to = new Email(email);
    Content content = new Content("text/plain", "Here is you login info \n\n username: "+username+"\tpassword: "+password);
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(emailServerPassword);
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      // System.out.println(response.getStatusCode());
      // System.out.println(response.getBody());
      // System.out.println(response.getHeaders());
    } catch (IOException ex) {
    }
    Notification n = new Notification();
    n.display("email sent", null);
  }
}


// import javax.mail.*;
// import javax.mail.internet.*;
// import java.util.*;

// public class EmailPasswordSend {

//    final String senderEmailID = "diaryappnoreply@gmail.com";
//     final String senderPassword = "DiaryApp6!";
//     final String emailSMTPserver = "smtp.sendgrid.net";
//     final String emailServerPort = "465";
//     final String emailServerUsername = "apikey";

//     String receiverEmailID = "zacharynewkirk88@gmail.com";
//     static String emailSubject = "Test Mail";
//     static String emailBody = ":)";

//     public EmailPasswordSend(String receiverEmailID, String emailSubject, String emailBody)
//     {
//         this.receiverEmailID=receiverEmailID;
//         this.emailSubject=emailSubject;
//         this.emailBody=emailBody;
//         Properties props = new Properties();
//         props.put("mail.smtp.user",senderEmailID);
//         props.put("mail.smtp.host", emailSMTPserver);
//         props.put("mail.smtp.port", emailServerPort);
//         props.put("mail.smtp.starttls.enable", "true");
//         props.put("mail.smtp.auth", "true");
//         props.put("mail.smtp.socketFactory.port", emailServerPort);
//         props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//         props.put("mail.smtp.socketFactory.fallback", "false");
//         SecurityManager security = System.getSecurityManager();
//         try
//         {
//             Authenticator auth = new SMTPAuthenticator();
//             Session session = Session.getInstance(props, auth);
//             MimeMessage msg = new MimeMessage(session);
//             msg.setText(emailBody);
//             msg.setSubject(emailSubject);
//             msg.setFrom(new InternetAddress(senderEmailID));
//             msg.addRecipient(Message.RecipientType.TO,
//             new InternetAddress(receiverEmailID));
//             Transport.send(msg);
//             System.out.println("Message send Successfully:)");
//         }
//         catch (Exception mex)
//         {
//             mex.printStackTrace();
//         }
//     }
//     public class SMTPAuthenticator extends javax.mail.Authenticator
//     {
//     public PasswordAuthentication getPasswordAuthentication()
//     {
//     return new PasswordAuthentication(senderEmailID, senderPassword);
//     }
//     }
//         public static void main(String[] args) {
//         EmailPasswordSend mailSender;
//             mailSender = new EmailPasswordSend("zacharynewkirk88@gmail.com","Testing Code 2 example","Testing Code Body yess");
//         }

//     }