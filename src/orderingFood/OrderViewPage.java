package orderingFood;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderViewPage {
    private final BorderPane rootPane;
    private ListView<String> listOrder;
    List<String> list;
    List<String> listcollect;

    public OrderViewPage() {
        VBox vbCenter = new VBox(); // use any container as center pane e.g. VBox
        // bottom respectively "button area"
        list = EdiblesList.getInstance().getList();
        String order = list.toString();
        String csv  = order.replace("[", "").replace("]", "")
                .replace(", ", "\n");
        listOrder = new ListView<String>(FXCollections.observableArrayList(csv));
//        listOrder.getItems().add(csv);
        HBox hbButtons = new HBox();
        Button confirm = new Button("Confirm Order!");
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

    public List<String> AddItemOrder(List<String> list) {
        int count = 0;
        int price = EdiblesList.getInstance().getPrice();
        int payment = 0;
        List<String> collect = new ArrayList<>();
        for (int i=0; i<list.size(); i++){
            for(int j=0; j<list.size(); j++) {
                if(i!=j && list.get(i).equals(list.get(j))) {
                    collect.add(list.get(j));
                } else { collect.add(list.get(i));}
            }
        }
        return collect;
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

            TextField addemail = new TextField();
            addemail.setPromptText("please enter your email");

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
            emailButton.setOnMouseClicked(mouseEvent -> {
                if (addemail.getText().length() == 0) {

                }
            });

            dialog.getDialogPane().setContent(grid);

            // Request focus on the email field by default.
            Platform.runLater(() -> addemail.requestFocus());

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                System.out.println("Your email: " + result.isPresent() + "has received code.");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success sending");
                alert.setHeaderText(null);
                alert.setContentText("We have already sent ordering code. Now, you can drive your car to " +
                        "food waiting point for payment and getting food.");
                alert.showAndWait();
            }

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == emailbtType) {
                    return new TextField();
                }
                return null;
            });
        }
    }

//    class AddOrderList implements EventHandler<ActionEvent> {
//        @Override
//        public void handle(ActionEvent event) {
//            Label orderName = EdiblesList.getInstance().getName();
//            listOrder.getItems().add(orderName);
//        }
//    }

}

