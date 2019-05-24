package javaFX;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/*
 * Ресурсы - файлы, которые распространяются с программой. Они "пакуются"
 * внутрь запускаемого файла. В Java ресурсы нужно хранить в каталоге
 * с исходниками. Ну или в отдельном специальном каталоге, который
 * можно отдельно настроить для хранения ресурсов.
 * В Java ресурсы, как и классы, принадлежат пакетам, поэтому важно,
 * в какое место внутри каталога src их положили. Обычно их кладут
 * в тот же пакет, что и у класса, который их использует.
 */

/*
 * Jar файлы - это аналог запускаемых файлов типа exe в Windows
 * Они содержат внутри себя
 *  - скомпилированные классы (.class файлы)
 *  - ресурсы
 *  - файл Манифеста. Описывает метаинформацию, например,
 *    кто автор приложения, версия приложения,
 *    может описывать запускаемый класс с main методом.
 *    Может содержать ссылки на библиотеки, если
 *    эти библиотеки уже не находятся сами внутри jar
 * Фактически, jar файл - это zip архив, при желании
 * его можно даже создавать вручную, архивируя содержимое
 *
 * jar = java archive
 * jar переводится как баночка, кувшин.
 */

public class ResourcesExamples extends Application {

    private Label title;
    private ImageView picture;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Пример про работу с ресурсами");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        loadText();
        loadImage();
    }

    private Parent initInterface() {
        //у нас будет два компонента: ImageView для отображения картинки
        //и Label под ним для отображения текста.
        //Картинка и текст будут взяты из ресурсов.
        picture = new ImageView();
        title = new Label();
        return new VBox(picture, title);
    }

    private void loadText() {
        //как же получить доступ к ресурсам.
        //1) к ресурсу можно получить доступ через InputStream
        try (
                InputStream text = ResourcesExamples
                        .class //это доступ к самому классу (см. рефлексия)
                        .getResourceAsStream("title.jp") //ресурс из того же
                                                              // пакета, что и класс
        ) {
            //рассчитываем, что хватит 1024 байт для чтения текста
            byte[] bytesFromInputStream = new byte[1024];
            //возвратит, сколько прочитано
            int read = text.read(bytesFromInputStream);
            //превращаем набор байт в текст, указываем, сколько байт брать из массива

            String titleText = new String(
                    bytesFromInputStream,
                    0, read,
                    StandardCharsets.UTF_8 // IDEA подсказала это вместо "utf-8"
            );

            //да, неудобно, что при чтении ресурса мы получем InputStream, но так
            //исторически сложилось, пользуемся тем, что есть. В других ситуациях
            //будут вспомогательные методы для чтения InputStream

            title.setText(titleText);
            title.setFont(Font.font(20));
        } catch (IOException e) {
            title.setText("Не удалось загрузить текст");
        }
    }

    private void loadImage() {
        //загружаем картинку (javafx.scene.Image)
        try (
                InputStream image = ResourcesExamples
                        .class //это доступ к самому классу (см. рефлексия)
                        .getResourceAsStream("cat.jpg") //ресурс из того же
                // пакета, что и класс
        ) {
            //Картинку можно создать напрямую из InputStream
            Image img = new Image(image);
            picture.setImage(img);
            //в какой размер уложить картинку
            picture.setFitWidth(400);
            picture.setFitHeight(400);
            picture.setPreserveRatio(true);
        } catch (IOException e) {
            //ничего не делаем, просто не будем загружать и всё
        }

        //2)
        //Доступ к ресурсу можно получить еще и через URL (java.net.URL)
        URL picURL = ResourcesExamples.class.getResource("cat.jpg");
        System.out.println("url: " + picURL);
        //картинку можно создавать и на основе URL, только надо привести к String
        new Image(picURL.toExternalForm());
    }
}
