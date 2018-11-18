package JavaFX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class HW_ImageViewer extends Application {
    private ListView<ImageContainer> imagesListView = new ListView<>();
    private TextField directoryName = new TextField();
    private Button chooseButton = new Button("Открыть");
    private ImageView image = new ImageView();
    private ObservableList<ImageContainer> imagesList = FXCollections.observableArrayList();
    private DirectoryChooser dirChooser = new DirectoryChooser();
    private Label selectImageLabel = new Label("Выберите изображение");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ImageViewer");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        initInteraction();

        // Связывание кнопки и выбора директории
        chooseButton.addEventHandler(
                ActionEvent.ACTION,
                a -> {
                    File dir = dirChooser.showDialog(primaryStage);
                    if (dir != null)
                        directoryName.setText(dir.getAbsolutePath());
                }
        );

        primaryStage.show();
    }

    private Parent initInterface() {
        HBox top_right = new HBox(directoryName, chooseButton);
        VBox right = new VBox(top_right, new Pane(image, selectImageLabel));

        HBox.setHgrow(directoryName, Priority.ALWAYS);

        SplitPane root = new SplitPane(imagesListView, right);
        root.setPrefSize(800, 600);
        root.setDividerPosition(0, 0.383);

        image.setFitWidth(400);
        image.setFitHeight(400);
        image.setPreserveRatio(true);
        image.setVisible(false);

        directoryName.setEditable(false);

        imagesListView.setCellFactory(
                lv -> new ListCell<ImageContainer>() {

                    @Override
                    public void updateItem(ImageContainer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                            setGraphic(null);
                        } else {
                            {
                                ImageView iv = new ImageView();
                                iv.setFitHeight(64);
                                iv.setFitWidth(64);
                                iv.setPreserveRatio(true);
                                iv.setImage(item.getImg());
                                setText(item.getFile().getName());
                                setGraphic(iv);
                            }
                        }
                    }
                }
        );

        // Надпись в пустом списке
        imagesListView.setPlaceholder(new Label("Нет изображений"));

        return root;
    }

    private void initInteraction() {
        // Свзяывание выбранной и отображаемой картинок
        imagesListView.getSelectionModel().selectedItemProperty().addListener(
                prop -> {
                    if (imagesListView.getSelectionModel().getSelectedItem() == null) {
                        image.setVisible(false);
                        selectImageLabel.setVisible(true);
                    } else {
                        if (!image.isVisible()) {
                            image.setVisible(true);
                            selectImageLabel.setVisible(false);
                        }
                        image.setImage(imagesListView.getSelectionModel().getSelectedItem().getImg());
                    }
                }
        );

        // При изменение директории в TextField теперь и список меняется
        directoryName.textProperty().addListener(
                (prop, oldValue, newValue) -> {
                    if (!oldValue.equals(newValue)) {
                        imagesListView.getItems().clear();
                        imagesList.removeAll();
                        initData();
                        dirChooser.setInitialDirectory((new File(newValue)).getParentFile());
                    }
                }
        );
    }

    // Функция для загрузки всех файлов из директории (не рекурсивно)
    private void initData() {
        File folder = new File(directoryName.getText());
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    ImageContainer newImg = new ImageContainer(file);
                    if (!newImg.isError())
                        imagesList.add(newImg);
                }
            }
        }

        imagesListView.setItems(imagesList);
    }
}