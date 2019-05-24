package fractals;

import javafx.scene.paint.Color;

public class WetSpotPalette implements Palette{
    @Override
    public Color getColor(double ind) {
        return Color.hsb(Math.random() * 360, ind, 1 - Math.random());
    }
}
