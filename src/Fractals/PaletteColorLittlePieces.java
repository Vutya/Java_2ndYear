package Fractals;

// Палитра Иры

import javafx.scene.paint.Color;

public class PaletteColorLittlePieces implements Palette {
    @Override
    public Color getColor(double ind) {
        for (int i = 0; i <= 5 ; i ++) {
            double j = 2 * i;

            if (j / 10 <= ind && ind < (j + 1) / 10) {
                double k = (ind - j / 10) * 10;
                return Color.hsb(k * 135 + 180, 0.9,0.9);
            }

            if ((j + 1) / 10 <= ind && ind < (j + 2) / 10) {
                double k = ((ind - 0.1 - j / 10) * 10 - 1) * (-1);
                return Color.hsb(k * 135 + 180, 0.9,0.9);
            }
        }
        return Color.gray(ind);
    }
}
