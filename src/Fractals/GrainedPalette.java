package Fractals;

import javafx.scene.paint.Color;

public class GrainedPalette implements Palette{
    @Override
    public Color getColor(double ind) {
        return Color.hsb(Math.random() * 360, Math.min(ind * 10, 1), 1 - ind);
    }
}
