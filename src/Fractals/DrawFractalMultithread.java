package Fractals;

import java.awt.image.BufferedImage;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

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

    private FileChooser fileChooser = new FileChooser();

    private Task<WritableImage> task = null;

    private Button savestate = new Button("Save State");
    private Button loadstate = new Button("Load State");
    private Button saveimg = new Button("Save Image");

    private Stage primaryStage;

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
        HBox buttons = new HBox(savestate, loadstate, saveimg);
        createFractalImage(400, 400);
        panel.getChildren().addAll(imgv);
        VBox.setVgrow(panel, Priority.ALWAYS);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        return new VBox(panel, buttons);
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
                    updateValue(new WritableImage(wi.getPixelReader(), width, height));
                    if (isCancelled())
                        return null;
                }
                return wi;
            }
        };

        new Thread(task).start();

        task.valueProperty().addListener(e -> imgv.setImage(task.getValue()));
        task.setOnSucceeded(e -> task = null);
    }

    private void initInteraction() {
        panel.widthProperty().addListener(prop -> updateImage());
        panel.heightProperty().addListener(prop -> updateImage());

        fileChooser.setInitialDirectory(new File("C:/Junkkkkkk"));
        fileChooser.setInitialFileName("fractal.png");
        fileChooser.getExtensionFilters().
                add(new FileChooser.ExtensionFilter("PNG Image (*.png)", "*.png"));

        saveimg.setOnAction(e -> saveImage(imgv.getImage()));
        savestate.setOnAction(e -> saveState());
        loadstate.setOnAction(e -> loadState());
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
            case DIGIT7:
                palette = new CutGradientBWPalette();
                updateImage();
                break;
            case DIGIT8:
                palette = new PaletteColorLittlePieces();
                updateImage();
                break;
        }
    }

    private void saveImage(Image img) {
        if (task.isRunning())
            return;
        File outputImg = fileChooser.showSaveDialog(primaryStage);
        BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
        try {
            ImageIO.write(bImage, "png", outputImg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadState() {
        try{
            Scanner sc = new Scanner(new File("params.txt")).useLocale(Locale.US);
            x0 = sc.nextDouble();
            y0 = sc.nextDouble();
            dx = sc.nextDouble();
            updateImage();
        } catch (FileNotFoundException e) {
            System.out.println("Whoops");
        }
    }

    private void saveState() {
        try(PrintStream prstr = new PrintStream("params.txt")) {
            prstr.println(x0);
            prstr.println(y0);
            prstr.println(dx);
        } catch (FileNotFoundException e) {
            System.out.println("Whoops");
        }
    }
}
