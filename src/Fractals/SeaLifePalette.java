package Fractals;

import javafx.scene.paint.Color;

public class SeaLifePalette implements Palette {
    private final int iters;

    SeaLifePalette(int iters) {
        this.iters = iters;
    }

    @Override
    public Color getColor(double ind) {
        return Color.hsb(iters * ind * 1.5 + 190, 1 - ind, 1 - ind);
    }
}
