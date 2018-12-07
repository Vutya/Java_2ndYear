package Fractals;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrawFractal extends Application {
    private int WIDTH = 400;
    private int HEIGHT = 400;

    private double x0 = -2; // у нас математические координаты, поле от -2 до 2, круг от -1 до 1
    private double y0 = 2; // но нужно понять, сколько нужно пикселей и как переходить, допустим, хотим, чтобы поле
    private double dx = 0.01; // рисовалось 400х400 пикселей. тогда в одной единице поля 100х100 пикселей, т.е.
                        //1 пиксель - 0,01 поля

    private Fractal fractal = new GradientCircle(0.5);
    private Palette palette = new GradientBWPalette();

    private ImageView imgv = new ImageView();
    private WritableImage wi = new WritableImage(WIDTH, HEIGHT);
    private PixelWriter pw = wi.getPixelWriter();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fractals");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        initInteraction();
        primaryStage.show();
    }

    private Parent initInterface() {
        imgv.setImage(wi);
        return new HBox(imgv);
    }

    private void initInteraction() {
        for (int xi = 0; xi < WIDTH - 1; xi++) {
            for (int yj = 0; yj < HEIGHT - 1; yj++) {
                double x = x0 + xi * dx;
                double y = y0 - yj * dx;
                double colorInd = fractal.getColor(x, y);
                Color color = palette.getColor(colorInd);
                pw.setColor(xi, yj, color);
            }
        }
    }
}
