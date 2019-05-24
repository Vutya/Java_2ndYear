package javaFX;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HW_Circle extends Application {
    private Label radius_label = new Label("Radius");
    private Label bgcol_label = new Label("Background color");
    private Label circlecol_label = new Label("Circle color");
    private Slider radius_slider = new Slider(10, 200, 120);
    private ColorPicker bg_color_picker = new ColorPicker();
    private ColorPicker circle_color_picker = new ColorPicker(Color.BLACK);

    private Circle circle = new Circle(120);
    private Pane bg = new Pane(circle);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Circle");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        primaryStage.show();

        initIneraction();
    }

    private Parent initInterface() {
        HBox root = new HBox();
        root.setPrefSize(700, 400);

        HBox radius = new HBox(radius_label, radius_slider);
        HBox bg_col = new HBox(bgcol_label, bg_color_picker);
        HBox cir_col = new HBox(circlecol_label, circle_color_picker);
        radius.setSpacing(5);
        bg_col.setSpacing(5);
        cir_col.setSpacing(5);

        VBox left = new VBox(radius, bg_col, cir_col);
        left.setSpacing(10);
        left.setPrefWidth(300);
        HBox.setHgrow(bg, Priority.ALWAYS);
        bg.setMinSize(10, 10);

        root.getChildren().addAll(left, bg);

        return root;
    }

    private void initIneraction() {
        circle.fillProperty().bind(circle_color_picker.valueProperty());
        /*circle_color_picker.valueProperty().addListener(
                col -> circle.setFill(circle_color_picker.getValue())
        );*/

        circle.radiusProperty().bind(radius_slider.valueProperty());
        /*radius_slider.valueProperty().addListener(
                rad -> circle.setRadius(radius_slider.getValue())
        );*/
        bg.backgroundProperty().bind(
                Bindings.createObjectBinding(
                        () -> new Background(
                                new BackgroundFill(
                                        bg_color_picker.getValue(),
                                        new CornerRadii(0),
                                        Insets.EMPTY
                                )
                        ), bg_color_picker.valueProperty()
                )
        );

        /*bg_color_picker.valueProperty().addListener(
                col -> bg.setBackground(new Background(
                        new BackgroundFill(
                                bg_color_picker.getValue(),
                                new CornerRadii(0),
                                Insets.EMPTY
                        )
                ))
        );*/

        circle.centerXProperty().bind(Bindings.createDoubleBinding(
                () -> bg.getWidth() / 2,
                bg.widthProperty()
        ));

        circle.centerYProperty().bind(Bindings.createDoubleBinding(
                () -> bg.getHeight() / 2,
                bg.heightProperty()
        ));

        radius_slider.maxProperty().bind(Bindings.createDoubleBinding(
                () -> Math.min(bg.getHeight(), bg.getWidth()) / 2,
                bg.heightProperty(),
                bg.widthProperty()
                )
        );
    }

}
