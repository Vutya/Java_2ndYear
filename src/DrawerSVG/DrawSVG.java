package DrawerSVG;

import java.io.FileNotFoundException;

public class DrawSVG {
    public static void main(String[] args) {
        Shape ps1 = new PositionedShape(new RedCircle(), 200, 200);
        Shape ps2 = new PositionedShape(new SmallSquare(), 300, 50);
        Shape ps3 = new PositionedShape(new RedCircle(), 80, 150);
        Shape ps4 = new PositionedShape(new SmallSquare(), 310, 90);

        try(SVG svg = new SVG("test.svg", 400, 400)) {
            ps1.draw(svg);
            ps2.draw(svg);
            ps3.draw(svg);
            ps4.draw(svg);
        } catch (FileNotFoundException e) {
            System.out.println("Could not create SVG file.");
        }
    }
}
