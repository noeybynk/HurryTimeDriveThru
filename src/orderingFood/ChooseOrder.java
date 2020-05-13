package orderingFood;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Random;

public class ChooseOrder extends Application {
    private Stage primaryStage;
    private List<EdiblesItem> list;
    private TextField number;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("HURRYTIME DRIVETHRU");
        VBox box = initComponent();
        Scene scene = new Scene(box, 350, 320);
        primaryStage.setScene(scene);
        primaryStage.setWidth(550);
        primaryStage.setHeight(400);
        primaryStage.show();
    }

    private VBox initComponent() {
        VBox root = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(addListEdibles(Category.values()[0].toString()));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(scrollPane);

        Label heading = new Label("HURRYTIME DRIVETHRU");
        heading.setFont(new Font("elephant", 32.0));

        Label menuLabel = new Label("Order History");

        menuLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                OrderViewPage his = new OrderViewPage();
                scrollPane.setContent(his.getRootpane());
            }
        });
        Menu menuHis = new Menu();
        menuHis.setGraphic(menuLabel);
        Menu menuCate = new Menu("Category");
        for(Category c : Category.values()) {
            MenuItem menuEdibles = new MenuItem(c.toString());
            menuEdibles.setOnAction(event -> {
                scrollPane.setContent(addListEdibles(c.toString()));
            });
            menuCate.getItems().add(menuEdibles);
        }
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) { System.exit(0); }
        });
        menuCate.getItems().addAll(new SeparatorMenuItem(), exit);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuCate, menuHis);

        root.getChildren().add(menuBar);
        root.getChildren().add(heading);
        root.getChildren().add(scrollPane);
        return root;
    }

    public VBox addListEdibles(String category) {
        Random rng = new Random();
        VBox imgBox = new VBox();
        imgBox.setSpacing(30);

        for (Edibles e : EdibleFactory.getEdibles(category)) {
            HBox horizon = new HBox(50);
            HBox addminus = new HBox(10);
            VBox description = new VBox(20);
            final ImageView image = new ImageView(e.getLink());
            image.setFitHeight(120);
            image.setPreserveRatio(true);
            image.setSmooth(true);
            image.setCache(true);

            Label name = new Label(e.nameWithSpace(e.toString()));
            name.setFont(new Font("elephant", 20.0));
            Label eachPrice = new Label(e.getPrice()+" Baht.");
            eachPrice.setFont(new Font("Arial", 18.0));
            list = EdiblesSingleton.getInstance().getList();
            TextField number = new TextField(String.valueOf(EdiblesSingleton.getInstance().findQuantity(name.getText())));
            number.setPrefWidth(50.0);

            Button minus = new Button("-");
            minus.setOnAction(event -> {
                decreaseNumber(number);
                int numInt = Integer.parseInt(number.getText());
                EdiblesSingleton.getInstance().removeList(e, numInt);
//                number.setText(String.valueOf(numInt));
            });
            Button add = new Button("+");
            add.setOnAction(event -> {
                System.out.println("+");
                int numInt = Integer.parseInt(number.getText());
                numInt++;
                increaseNumber(number);
                EdiblesSingleton.getInstance().addList(e, numInt);
            });
            addminus.getChildren().addAll(minus, number, add);
            description.getChildren().addAll(name, eachPrice, addminus);
            horizon.getChildren().addAll(image, description);
            String style = String.format("-fx-background: rgb(%d, %d, %d);"+
                            "-fx-background-color: -fx-background;",
                    rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256));
            horizon.setStyle(style);
            imgBox.getChildren().addAll(horizon);
        }
        return imgBox;
    }

    public void decreaseNumber(TextField textField) {
        int total = 0;
        if(textField.getText().length() <= 0) {
            textField.setText(String.valueOf(total));
        } else {
            int strtoInt = Integer.parseInt(textField.getText());
            if(strtoInt == 0) {
                return;
            }
            strtoInt--;
            textField.setText(String.valueOf(strtoInt));
        }
    }
    public void increaseNumber(TextField textField) {
        int total = 0;
        if(textField.getText().length() <= 0) {
            total++;
            textField.setText(String.valueOf(total));
        } else {
            int strtoInt = Integer.parseInt(textField.getText());
            strtoInt++;
            textField.setText(String.valueOf(strtoInt));
        }
    }

}

