package orderingFood;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

/**
 * This class is for sending java mail to the client's email to drive their car to food pick up point to
 * get their orders. It was developed by java mail and handle alert-dialog to get client's email.
 */
public class EnterEmail implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        Dialog dialog = new Dialog();
//            TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Email Sending");
        dialog.setHeaderText("For sending the food receiving code.");

        // Set the icon (must be included in the project).
        ImageView img = new ImageView("https://s3-ap-southeast-1.amazonaws.com/img-in-th/1b05fc064cd3b611b4a52537be705963.png");
        img.setFitWidth(50);
        img.setFitHeight(40);
        dialog.setGraphic(img);

        TextField addemail = new TextField();
        addemail.setPromptText("Please input your gmail address");

        // Set the button types.
        ButtonType emailbtType = new ButtonType("confirm!", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(emailbtType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("email: "), 0, 0);
        grid.add(addemail, 1, 0);

        // Enable/Disable confirm button depending on whether a username was entered.
        Node emailButton = dialog.getDialogPane().lookupButton(emailbtType);
        emailButton.setVisible(true);

        dialog.getDialogPane().setContent(grid);

        Optional result = dialog.showAndWait();
        if (result.get() == emailbtType) {
            resultForDialog(addemail);
        }
//            if (result.get() == ButtonType.CANCEL) {
//                System.out.println("out");
//            }

        if(result.get() == addemail) {
            addemail.setOnKeyPressed(e -> {
                resultForDialog(addemail);
            });
        }
    }

    /** Showing alert in different case after you input your email.*/
    public void resultForDialog(TextField addemail) {
        if(addemail.getText().length() > 0) {
            try {
                generateAndSendEmail(addemail);
                OTP(6);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success sending");
                alert.setHeaderText(null);
                alert.setContentText("We have already sent ordering code. Now, you can drive your car to " +
                        "food waiting point for payment and getting food.");
                alert.showAndWait();
                System.exit(0);
            } catch (AddressException ae) {
                System.out.println("error" + ae.getMessage());
            } catch (MessagingException me) {
                System.out.println("error: " + me.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Email Address");
                alert.setHeaderText(null);
                alert.setContentText("Cannot find your email address, please try again!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required Fields Empty");
            alert.setContentText("The fields highlighted in red must be filled out.\nPlease try again.");
            alert.showAndWait();
        }
    }

    /** using java mail to transport the mail from java to gmail.*/
    public void generateAndSendEmail(TextField email) throws AddressException, MessagingException{

        String smtpHost="smtp.gmail.com";
        String smtpUser="hurrytime.drivethru@gmail.com";
        String smtpPassword="HurryTime123";
        int smtpPort=587;//Port may vary.Check yours smtp port
        // Step1
//        System.out.println("\n 1st ===> setup Mail Server Properties..");
        Properties mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.host", smtpHost);
        mailServerProperties.put("mail.smtp.user", smtpUser);
        mailServerProperties.put("mail.smtp.password", smtpPassword);
        mailServerProperties.put("mail.smtp.port", smtpPort);

        mailServerProperties.put("mail.smtp.starttls.enable", "true");
//        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
//        System.out.println("\n\n 2nd ===> get Mail Session..");
        Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.setFrom (new InternetAddress(smtpUser));
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getText().trim()));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(smtpUser));
        generateMailMessage.setSubject("Payment for Ordering food");
        String emailBody = "Dear Valued Customers, <br><br> Now, our staff is preparing your order.  " +
                "Please drive to the Food Pick Up Point to get your order by showing OTP that we sent to you " +
                "via e-mail. The payment can be settled by cash, E-wallet or debit/credit card.<br>" +
                "Thank you for ordering with us.  We hope to serve you again in the near future. <br><br>" +
                "Yours sincerely,<br> Hurrytime Drivethru<br><br>Your OTP is : " + new String(OTP(6));

        generateMailMessage.setContent(emailBody, "text/html");
//        System.out.println("Mail Session has been created successfully..");

        // Step3
//        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect(smtpHost,smtpPort, smtpUser, smtpPassword);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    /** creating the OTP number for each client. It can use only one time.*/
    public char[] OTP(int len) {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }
}