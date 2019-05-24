package fractals;

import javafx.scene.paint.Color;

public class CutGradientBWPalette implements Palette {
    @Override
    public Color getColor(double ind) {
        for (int i = 0; i < 5; i++) {
            if (ind >= i * 0.2 && ind <= i * 0.2 + 0.1)
                return Color.gray((1 - (i * 0.2 + 0.1 - ind) * 10));
            else if (ind >= i * 0.2 + 0.1 && ind <= i * 0.2 + 0.2)
                return Color.gray((i * 0.2 + 0.2 - ind) * 10);
        }
        return Color.gray(1 - Math.min(10 * ind, 1));
    }
}
