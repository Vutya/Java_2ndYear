package Java12Test;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// подключаем jar'ы в Project Structure

public class CheckJavaFX extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Button b = new Button("hello");
        stage.show();
    }
}
