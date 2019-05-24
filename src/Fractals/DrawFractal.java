package fractals;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
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
    private Palette palette = new BlackWhitePalette();

    private WritableImage frimg;
    private ImageView imgv = new ImageView();
    private Pane panel = new Pane();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("fractals");

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
        return panel;
    }

    private WritableImage createFractalImage(int width, int height, int xc, int yc) {
        if (xc == 0 && yc == 0) {
            WritableImage wi = new WritableImage(width, height);
            PixelWriter pw = wi.getPixelWriter();

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
        } else {
            WritableImage wi = new WritableImage(width, height);
            PixelReader pr = frimg.getPixelReader();
            PixelWriter pw = wi.getPixelWriter();

            for (int xi = 0; xi < Math.min(panel.getWidth(), frimg.getWidth()) - 1; xi++) {
                for (int yj = 0; yj < Math.min(panel.getHeight(), frimg.getHeight()) - 1; yj++) {
                    pw.setColor(xi, yj, pr.getColor(xi, yj));
                }
            }

            if (xc > 0) {
                for (int xi = (int) Math.min(panel.getWidth(), frimg.getWidth()) - 1; xi < width - 1; xi++) {
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
                    for (int yj = (int)Math.min(panel.getHeight(), frimg.getHeight()) - 1; yj < height - 1; yj++) {
                        double x = x0 + xi * dx;
                        double y = y0 - yj * dx;
                        double colorInd = fractal.getColor(x, y);
                        Color color = palette.getColor(colorInd);
                        pw.setColor(xi, yj, color);
                    }
                }
            }
            return wi;
        }
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
        if (xc < 0 && yc < 0)
            return;
        if (panel.getWidth() != 0 && panel.getHeight() != 0) {
            frimg = createFractalImage((int) (panel.getWidth()), (int) panel.getHeight(), xc, yc);
            imgv.setImage(frimg);
        }
    }

    private void move(KeyCode pressedKey) {
        switch(pressedKey) {
            case UP:
                y0 += step * dx;
                break;
            case DOWN:
                y0 -= step * dx;
                break;
            case LEFT:
                x0 -= step * dx;
                break;
            case RIGHT:
                x0 += step * dx;
                break;
            case EQUALS:
            case ADD:
                x0 += 0.5 * panel.getWidth() * (dx - dx / ratio);
                y0 -= 0.5 * panel.getHeight() * (dx - dx / ratio);
                dx /= ratio;
                break;
            case MINUS:
            case SUBTRACT:
                x0 += 0.5 * panel.getWidth() * (dx - dx * ratio);
                y0 -= 0.5 * panel.getHeight() * (dx - dx * ratio);
                dx *= ratio;
                break;
            case DIGIT1:
                palette = new BlackWhitePalette();
                break;
            case DIGIT2:
                palette = new GradientBWPalette();
                break;
            case DIGIT3:
                palette = new BloodVesselsPalette();
                break;
            case DIGIT4:
                palette = new BlackHoleSunPalette(iters);
                break;
            case DIGIT5:
                palette = new SeaLifePalette(iters);
                break;
            case DIGIT6:
                palette = new WetSpotPalette();
                break;
            case DIGIT7:
                palette = new CutGradientBWPalette();
                break;
            case DIGIT8:
                palette = new PaletteColorLittlePieces();
                break;
        }
        updateImage(0, 0);
    }
}
