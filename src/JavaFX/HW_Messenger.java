package javaFX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HW_Messenger extends Application {
    private ObservableList<String> names = FXCollections.observableArrayList("Roma", "Talia", "Dasha", "Arno");

    private Label contacts = new Label("Contacts");
    private ListView<String> contacts_list = new ListView<>();
    private Button send = new Button("Send");
    private TextArea dialog = new TextArea();
    private TextField new_msg = new TextField();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("This is Telegram");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        initInteraction();

        primaryStage.show();
    }

    private Parent initInterface() {
        HBox root = new HBox();
        VBox left = new VBox(dialog, new HBox(new_msg, send));
        VBox right = new VBox(contacts, contacts_list);

        root.getChildren().addAll(left, right);

        HBox.setHgrow(new_msg, Priority.ALWAYS);
        VBox.setVgrow(contacts_list, Priority.ALWAYS);
        VBox.setVgrow(dialog, Priority.ALWAYS);
        HBox.setHgrow(left, Priority.ALWAYS);

        contacts_list.setPrefWidth(190);
        contacts_list.setItems(names);

        return root;
    }

    private void initInteraction() {
        EventHandler<ActionEvent> sendAction = (a) -> {
            dialog.appendText("You to " + contacts_list.getSelectionModel().getSelectedItem() + ": "
                    + new_msg.getText() + "\n");
            new_msg.textProperty().setValue("");
        };

        send.setOnAction(sendAction);
        new_msg.setOnAction(sendAction);

        dialog.setEditable(false);
    }
}
