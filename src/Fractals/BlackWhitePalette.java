package Fractals;

import javafx.scene.paint.Color;

public class BlackWhitePalette implements Palette {
    @Override
    public Color getColor(double ind) {
        if (ind < 0.5)
            return Color.WHITE;
        else
            return Color.BLACK;
    }
}
