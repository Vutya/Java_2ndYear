package JavaFX;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageContainer {
    private File file;
    private Image img;

    ImageContainer(File f) {
        file = f;
        try {
            img = new Image(new FileInputStream(f));
        } catch (IOException e) {
            // Привет
        }
    }

    public File getFile() {
        return file;
    }

    public Image getImg() {
        return img;
    }

    public boolean isError() {
        return img.isError();
    }
}
