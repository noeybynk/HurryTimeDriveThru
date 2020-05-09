package orderingFood;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.scene.control.TextField;

public class CrunchifyEmail {
    private TextField toEmail;

    //static Properties mailServerProperties;
    // static Session getMailSession;
    //  static MimeMessage generateMailMessage;

//    public static void main(String args[]) throws AddressException, MessagingException {
//        generateAndSendEmail();
//        System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
//    }
    public CrunchifyEmail(TextField toEmail) {
        this.toEmail = toEmail;
    }

    public static void generateAndSendEmail(TextField email) throws AddressException, MessagingException {

        String smtpHost="smtp.gmail.com";
        String smtpUser="boonyanuch.noey2000@gmail.com";
        String smtpPassword="nnynoey040443";
        int smtpPort=587;//Port may vary.Check yours smtp port
        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        Properties mailServerProperties = System.getProperties();
        //mailServerProperties.put("mail.smtp.ssl.trust", smtpHost);
//        mailServerProperties.put("mail.smtp.starttls.enable", true); // added this line
        mailServerProperties.put("mail.smtp.host", smtpHost);
        mailServerProperties.put("mail.smtp.user", smtpUser);
        mailServerProperties.put("mail.smtp.password", smtpPassword);
        mailServerProperties.put("mail.smtp.port", smtpPort);

        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.setFrom (new InternetAddress (smtpUser));
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getText().trim()));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("boonyanuch.noey2000@gmail.com"));
        generateMailMessage.setSubject("Greetings from Crunchify..");
        String emailBody = "Sawasdeeeeeeeeeeee i jao fann :P " + "<br><br> love and good night!, <br>Your hanoey<3";
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect(smtpHost,smtpPort, smtpUser, smtpPassword);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}