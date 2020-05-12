package orderingFood;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class OrderViewPage {
    private final BorderPane rootPane;
    private ListView<String> listOrder;
    List<EdiblesItem> list;

    public OrderViewPage() {
        VBox vbCenter = new VBox(); // use any container as center pane e.g. VBox
        // bottom respectively "button area"
        list = EdiblesSingleton.getInstance().getList();
        String order = list.toString();
        String csv  = order.replace("[", "").replace("]", "")
                .replace(", ", "\n");
        listOrder = new ListView<String>(FXCollections.observableArrayList(csv));

        HBox hbButtons = new HBox();
        Button confirm = new Button("Place Order!");
        confirm.setOnAction(new EnterEmail());
        hbButtons.getChildren().add(confirm);
        hbButtons.setAlignment(Pos.BOTTOM_RIGHT);
        vbCenter.getChildren().addAll(listOrder);

        // root
        rootPane = new BorderPane();
        BorderPane bottom = new BorderPane();
        rootPane.setPadding(new Insets(20)); // space between elements and window border
        rootPane.setCenter(vbCenter);
        bottom.setRight(hbButtons);
        rootPane.setBottom(bottom);
    }
    public Pane getRootpane() { return rootPane; }

    class EnterEmail implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Dialog dialog = new Dialog();
//            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Email Sending");
            dialog.setHeaderText("For sending the receiving code.");

            // Set the icon (must be included in the project).
            ImageView img = new ImageView("https://s3-ap-southeast-1.amazonaws.com/img-in-th/1b05fc064cd3b611b4a52537be705963.png");
            img.setFitWidth(50);
            img.setFitHeight(40);
            dialog.setGraphic(img);

            TextField addemail = new TextField();
//            if(addemail.getText().length() > 0) {
//                try {
//                    System.out.println("eiei");
//                } catch (NumberFormatException nfe) {
//                    addemail.setStyle("-fx-border-color: red;");
//                    addemail.setPromptText("please enter your email");
//                }
//            }

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

            // Request focus on the email field by default.
//            Platform.runLater(() -> addemail.requestFocus());

//            Optional<String> result = dialog.showAndWait();
//            if (result.isPresent()) {
//                System.out.println("Your email: " + result.isPresent() + "has received code.");
////                emailButton.setOnMouseClicked(mouseEvent -> {
////                    if(addemail.getText().length() > 0) {
////                        try {
////                            System.out.println("eiei");
////                        } catch (NumberFormatException nfe) {
////                            addemail.setStyle("-fx-border-color: red;");
////                            addemail.setPromptText("please enter your email");
////                        }
////                    }
////                });
//            }

            Optional<ButtonType> result = dialog.showAndWait();
            result.ifPresent(response -> {
                if (result.get() == emailbtType) {
                    if (addemail.getText().length() > 0) {
                        try {
                            System.out.println("have text");
                            generateAndSendEmail(addemail);
                            OTP(6);

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success sending");
                            alert.setHeaderText(null);
                            alert.setContentText("We have already sent ordering code. Now, you can drive your car to " +
                                    "food waiting point for payment and getting food.");
                            alert.showAndWait();
                        } catch (AddressException ae) {
                            System.out.println("error" + ae.getMessage());
                        } catch (MessagingException me) {
                            System.out.println("error: msggg " + me.getMessage());
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error Email Address");
                            alert.setHeaderText(null);
                            alert.setContentText("Cannot find your email address, please try again!");
                            alert.showAndWait();
                        }
                    } else {
                        System.out.println("no text");
                        addemail.setStyle("-fx-border-color: red;");
                        addemail.setPromptText("please enter your email");
                        event.consume();
                    }
                    System.out.println("ok");
                }
            });
                if (result.get() == ButtonType.CANCEL) {
                    System.out.println("out");
                }

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == emailbtType) {
                    return new TextField();
                }
                return null;
            });
        }
    }

    public void generateAndSendEmail(TextField email) throws AddressException, MessagingException{

        String smtpHost="smtp.gmail.com";
        String smtpUser="hurrytime.drivethru@gmail.com";
        String smtpPassword="HurryTime123";
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
        generateMailMessage.setSubject("Payment for Ordering food");
        String emailBody = "Dear Valued Customers, <br><br> Now, our staff is preparing your order.  " +
                "Please drive to the Food Pick Up Point to get your order by showing OTP that we sent to you " +
                "via e-mail. The payment can be settled by cash, E-wallet or debit/credit card.<br>" +
                "Thank you for ordering with us.  We hope to serve you again in the near future. <br><br>" +
                "Yours sincerely,<br> Hurrytime Drivethru<br><br>Your OTP is : " + new String(OTP(6));

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

    public char[] OTP(int len) {
//        System.out.println("Generating OTP using random() : ");
        System.out.print("Your OTP is : ");

        // Using numeric values
        String numbers = "0123456789";

        // Using random method
        Random rndm_method = new Random();

        char[] otp = new char[len];

        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }
}

