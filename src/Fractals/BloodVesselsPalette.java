package Fractals;

import javafx.scene.paint.Color;

public class BloodVesselsPalette implements Palette {
    @Override
    public Color getColor(double ind) {
        return Color.hsb(ind, Math.min(ind * 10, 1), 1 - ind); // Blood Vessels, Black Heart
        // return Color.hsb(ind, Math.min(ind * 10, 1), 0.9); // Blood Vessels
    }
}
