package WhereIsItGame;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class WhereIsIt extends Application {
    private Label label = new Label("\"Where Is It\" Game");
    private Text text = new Text("Kek");
    private ImageView imgv = new ImageView();
    private ArrayList<Question> quests = new ArrayList<>();

    private final int total = 5;
    private int currentQuestion = 0;
    private int correct = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Where Is It?");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        initInteraction();
        startGame();
        primaryStage.show();
    }

    private Parent initInterface() {
        HBox top = new HBox(label, text);
        HBox bot = new HBox(imgv);

        try (
                InputStream image = WhereIsIt.class.getResourceAsStream("little_pug.jpg")
        ) {
            Image img = new Image(image);
            imgv.setImage(img);
            imgv.setFitWidth(512);
            imgv.setFitHeight(288);
            imgv.setPreserveRatio(true);
        } catch (IOException e) {
            //ничего не делаем, просто не будем загружать и всё
        }

        text.setTextAlignment(TextAlignment.RIGHT);

        return new VBox(top, bot);
    }

    private void initInteraction() {
        imgv.addEventHandler(MouseEvent.MOUSE_CLICKED, this::update);
    }

    private void startGame() {
        try (
                InputStream in =  WhereIsIt.class.getResourceAsStream("test.txt")
        ) {
            Scanner scan = new Scanner(in);
            for (int i = 0; i < total; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                int width = scan.nextInt();
                int height = scan.nextInt();
                String quest = scan.nextLine();

                quests.add(new Question(quest, x, y, width, height));
            }
            text.setText(quests.get(currentQuestion).getQuestion());
        } catch (IOException e) {
            System.out.println("Не удалось загрузить текст");
        }
    }

    private void update(MouseEvent evt) {
        Question q = quests.get(currentQuestion);
        if (evt.getX() >= q.getX() && evt.getX() <= q.getX() + q.getWidth()
                && evt.getY() >= q.getY() && evt.getY() <= q.getY() + q.getHeight())
            correct += 1;
        currentQuestion += 1;
        if (currentQuestion >= total) {
            reset();
        } else
            text.setText(quests.get(currentQuestion).getQuestion());
    }

    private void reset() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Вы правильно ответили на " + correct + " из " + total + " вопросов.", ButtonType.OK);
        alert.showAndWait();
        currentQuestion = 0;
        correct = 0;
        text.setText(quests.get(currentQuestion).getQuestion());
    }
}
