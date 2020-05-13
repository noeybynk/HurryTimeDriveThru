package orderingFood;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.*;

/**
 * This class is for showing the order history that you had already chosen. It's called from the button
 * in the main page that will send value to this page. and have the button to send the code for client
 * to get the food at the food pick up point.
 */
public class OrderViewPage {
    private final BorderPane rootPane;
    private ListView<String> listOrder;
    List<EdiblesItem> list;

    /** Containing the list of edible item and the button in the Vbox.*/
    public OrderViewPage() {
        VBox vbCenter = new VBox(10); // use any container as center pane e.g. VBox
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

        Label head = new Label("Order History");
        head.setFont(new Font("Berlin Sans FB", 20));
        head.setAlignment(Pos.TOP_CENTER);
        vbCenter.getChildren().addAll(head, listOrder);

        // root
        rootPane = new BorderPane();
        BorderPane bottom = new BorderPane();
        rootPane.setPadding(new Insets(20)); // space between elements and window border
        rootPane.setCenter(vbCenter);
        bottom.setRight(hbButtons);
        rootPane.setBottom(bottom);
    }

    //getting method to get the rootpane to use.
    public Pane getRootpane() { return rootPane; }
}