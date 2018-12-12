package Fractals;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrawFractal extends Application {
    private double x0 = -2;
    private double y0 = 2;
    private double dx = 0.01;

    private int step = 100;
    private double ratio = 1.3;
    private int iters = 1000;

    private Fractal fractal = new Mandelbrot(iters);
    private Palette palette = new BloodVesselsPalette();

    private WritableImage frimg;
    private ImageView imgv = new ImageView();
    private Pane panel = new Pane();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fractals");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,
                event -> move(event.getCode()));

        initInteraction();
        primaryStage.show();
    }

    private Parent initInterface() {
        frimg = createFractalImage(400, 400, 0, 0);
        imgv.setImage(frimg);
        panel.getChildren().addAll(imgv);
        HBox.setHgrow(imgv, Priority.ALWAYS);
        return panel;
    }

    private WritableImage createFractalImage(int width, int height, int xc, int yc) {
        if (xc < 0 || yc < 0)
            return frimg;

        WritableImage wi = new WritableImage(width, height);
        PixelWriter pw = wi.getPixelWriter();

        if (xc > 0) {
            for (int xi = 0; xi < xc; xi++) {
                for (int yj = 0; yj < height - 1; yj++) {
                    double x = x0 + xi * dx;
                    double y = y0 - yj * dx;
                    double colorInd = fractal.getColor(x, y);
                    Color color = palette.getColor(colorInd);
                    pw.setColor(xi, yj, color);
                }
            }
        }

        if (yc > 0) {
            for (int xi = 0; xi < width - 1; xi++) {
                for (int yj = 0; yj < yc; yj++) {
                    double x = x0 + xi * dx;
                    double y = y0 - yj * dx;
                    double colorInd = fractal.getColor(x, y);
                    Color color = palette.getColor(colorInd);
                    pw.setColor(xi, yj, color);
                }
            }
        }

        for (int xi = xc; xi < width - 1; xi++) {
            for (int yj = yc; yj < height - 1; yj++) {
                double x = x0 + xi * dx;
                double y = y0 - yj * dx;
                double colorInd = fractal.getColor(x, y);
                Color color = palette.getColor(colorInd);
                pw.setColor(xi, yj, color);
            }
        }
        return wi;
    }

    private void initInteraction() {
        panel.widthProperty().addListener(
               prop -> updateImage((int) (panel.getWidth() - frimg.getWidth()), 0)
        );

        panel.heightProperty().addListener(
                prop -> updateImage(0, (int) (panel.getHeight() - frimg.getHeight()))
        );
    }

    private void updateImage(int xc, int yc) {
        if (panel.getWidth() != 0 && panel.getHeight() != 0) {
            frimg = createFractalImage((int) (panel.getWidth()), (int) panel.getHeight(), xc, yc);
            imgv.setImage(frimg);
        }
    }

    private void move(KeyCode pressedKey) {
        switch(pressedKey) {
            case UP:
                y0 += step * dx;
                updateImage(0, step);
                break;
            case DOWN:
                y0 -= step * dx;
                updateImage(0, step);
                break;
            case LEFT:
                x0 -= step * dx;
                updateImage(step, 0);
                break;
            case RIGHT:
                x0 += step * dx;
                updateImage(step, 0);
                break;
            case EQUALS:
            case ADD:
                x0 += 0.5 * panel.getWidth() * (dx - dx / ratio);
                y0 -= 0.5 * panel.getHeight() * (dx - dx / ratio);
                dx /= ratio;
                updateImage(0, 0);
                break;
            case MINUS:
            case SUBTRACT:
                x0 += 0.5 * panel.getWidth() * (dx - dx * ratio);
                y0 -= 0.5 * panel.getHeight() * (dx - dx * ratio);
                dx *= ratio;
                updateImage(0, 0);
                break;
        }
    }
}
