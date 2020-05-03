package orderingFood;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.util.Optional;

public class OrderViewPage {
    private final BorderPane rootPane;

    public OrderViewPage() {
        VBox vbCenter = new VBox(); // use any container as center pane e.g. VBox
        TextField console = new TextField();
        vbCenter.getChildren().add(console);

        // bottom respectively "button area"
        HBox hbButtons = new HBox();
        Button confirm = new Button("Confirm Order!");
        confirm.setOnAction(new EnterEmail());
        hbButtons.getChildren().add(confirm);
        hbButtons.setAlignment(Pos.BOTTOM_RIGHT);

        // root
        rootPane = new BorderPane();
        BorderPane bottom = new BorderPane();
        rootPane.setPadding(new Insets(20)); // space between elements and window border
        rootPane.setCenter(vbCenter);
        bottom.setRight(hbButtons);
        rootPane.setBottom(bottom);
    }
    public Pane getRootpane() {
        return rootPane;
    }

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
//            dialog.setContentText("Please enter your email:");
            // Set the button types.
            ButtonType emailbtType = new ButtonType("confirm!", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(emailbtType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField addemail = new TextField();
            addemail.setPromptText("please enter your email");

            grid.add(new Label("email: "), 0, 0);
            grid.add(addemail, 1, 0);

            // Enable/Disable confirm button depending on whether a username was entered.
            Node emailButton = dialog.getDialogPane().lookupButton(emailbtType);
            emailButton.setVisible(true);
            emailButton.setOnMouseClicked(show -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success sending");
                alert.setHeaderText(null);
                alert.setContentText("We have already sent ordering code. Now, you can drive your car to " +
                        "food waiting point for payment and getting food.");
                alert.showAndWait();
            });

            dialog.getDialogPane().setContent(grid);

            // Request focus on the email field by default.
            Platform.runLater(() -> addemail.requestFocus());

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                System.out.println("Your email: " + result.get() + "has received code.");
            }

            // Convert the result to a username-password-pair when the login button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == emailbtType) {
                    return new TextField();
                }
                return null;
            });
        }
    }
}