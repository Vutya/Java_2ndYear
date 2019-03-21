package Fractals;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrawFractalMultithread extends Application {
    private double x0 = -2;
    private double y0 = 2;
    private double dx = 0.01;

    private int step = 100;
    private double ratio = 1.3;
    private int iters = 1000;

    private Fractal fractal = new Mandelbrot(iters);
    private Palette palette = new BlackWhitePalette();

    private ImageView imgv = new ImageView();
    private Pane panel = new Pane();

    private Task<WritableImage> task = null;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fractals");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root, 400, 400));

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,
                event -> move(event.getCode()));

        initInteraction();
        primaryStage.show();
    }

    private Parent initInterface() {
        createFractalImage(400, 400);
        panel.getChildren().addAll(imgv);
        return panel;
    }

    private void createFractalImage(int width, int height) {
        if (task != null) {
            task.cancel();
        }

        task = new Task<WritableImage>() {

            @Override
            protected WritableImage call() throws Exception {
                WritableImage wi = new WritableImage(width, height);
                PixelWriter pw = wi.getPixelWriter();

                for (int xi = 0; xi < width - 1; xi++) {
                    for (int yj = 0; yj < height - 1; yj++) {
                        double x = x0 + xi * dx;
                        double y = y0 - yj * dx;
                        double colorInd = fractal.getColor(x, y);
                        Color color = palette.getColor(colorInd);
                        pw.setColor(xi, yj, color);
                    }
                    updateValue(copy(wi));
                    // Попросить Посова пояснить
                    if (isCancelled())
                        return null;
                }
                return wi;
            }
        };

        new Thread(task).start();

        task.valueProperty().addListener(e -> imgv.setImage(task.getValue()));
        task.onSucceededProperty().addListener(e -> task = null);
    }

    private void initInteraction() {
        panel.widthProperty().addListener(prop -> updateImage());
        panel.heightProperty().addListener(prop -> updateImage());
    }

    private void updateImage() {
        if (panel.getWidth() != 0 && panel.getHeight() != 0) {
            createFractalImage((int) (panel.getWidth()), (int) panel.getHeight());
        }
    }

    private void move(KeyCode pressedKey) {
        switch(pressedKey) {
            case UP:
                y0 += step * dx;
                updateImage();
                break;
            case DOWN:
                y0 -= step * dx;
                updateImage();
                break;
            case LEFT:
                x0 -= step * dx;
                updateImage();
                break;
            case RIGHT:
                x0 += step * dx;
                updateImage();
                break;
            case EQUALS:
            case ADD:
                x0 += 0.5 * panel.getWidth() * (dx - dx / ratio);
                y0 -= 0.5 * panel.getHeight() * (dx - dx / ratio);
                dx /= ratio;
                updateImage();
                break;
            case MINUS:
            case SUBTRACT:
                x0 += 0.5 * panel.getWidth() * (dx - dx * ratio);
                y0 -= 0.5 * panel.getHeight() * (dx - dx * ratio);
                dx *= ratio;
                updateImage();
                break;
            case DIGIT1:
                palette = new BlackWhitePalette();
                updateImage();
                break;
            case DIGIT2:
                palette = new GradientBWPalette();
                updateImage();
                break;
            case DIGIT3:
                palette = new BloodVesselsPalette();
                updateImage();
                break;
            case DIGIT4:
                palette = new BlackHoleSunPalette(iters);
                updateImage();
                break;
            case DIGIT5:
                palette = new SeaLifePalette(iters);
                updateImage();
                break;
            case DIGIT6:
                palette = new WetSpotPalette();
                updateImage();
                break;
            case S:
                saveImage();
                break;
        }
    }

    private WritableImage copy(WritableImage img) {
        PixelReader pxreader = img.getPixelReader();
        int height = (int)img.getHeight();
        int width = (int)img.getWidth();
        WritableImage wi = new WritableImage(width, height);
        PixelWriter pw = wi.getPixelWriter();
        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++)
                pw.setColor(x, y, pxreader.getColor(x, y));
        }
        return wi;
    }

    private void saveImage() {

    }
}

// - сохранить JPEG
//- загрузить положение x,y,dx PrintStream, Scanner
