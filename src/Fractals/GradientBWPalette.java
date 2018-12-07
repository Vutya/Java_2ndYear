package Fractals;

import javafx.scene.paint.Color;

public class GradientBWPalette implements Palette {
    @Override
    public Color getColor(double ind) {
        return Color.gray(ind);
    }
}
