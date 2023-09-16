package EmailSystem;

// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import de.milchreis.uibooster.components.Notification;
import util.API.KeyReader;

import java.io.IOException;

public class EmailPasswordSend {
  final static Email from = new Email("diaryappnoreply@gmail.com");
  final static String subject = "Login Info";

  public static void sendEmail(String email, String password, String username){
    // System.out.println("starting send method");
    Email to = new Email(email);
    Content content = new Content("text/plain", "Here is your login info \n\n username: "+username+"\tpassword: "+password);
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(KeyReader.getAccessKey());
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