package fractals;

import javafx.scene.paint.Color;

public class BlackHoleSunPalette implements Palette {
    private final int iters;

    BlackHoleSunPalette(int iters) {
        this.iters = iters;
    }

    @Override
    public Color getColor(double ind) {
        return Color.hsb(iters * ind * 3, 1 - ind, 1 - ind); // Black Hole Sun
        // return Color.hsb(iters * ind * 3, 1 - Math.min(ind * 10, 1), 1 - ind); // Smoothed Black Hole Sun
    }
}
