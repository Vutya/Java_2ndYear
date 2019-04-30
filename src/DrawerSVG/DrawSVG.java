package DrawerSVG;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public class DrawSVG {
    public static void main(String[] args) {
        Optional<Long> randSeed = Settings.getInstance().getRandSeed();
        Random rnd = randSeed.map(Random::new).orElseGet(Random::new);

        ShapeFactory factory = new ShapeFactory();
        String rc = Settings.getInstance().getShapeDescription("red_circle");
        String ss = Settings.getInstance().getShapeDescription("small_square");

        HashMap<String, Integer> shapes = Settings.getInstance().getShapesWithCount();

        try (SVG svg = new SVG("test.svg")) {
            for (int i = 0; i < shapes.get("red_circle"); i++) {
                Shape circle = new PositionedShape(factory.create(rc), rnd.nextInt(401), rnd.nextInt(401));
                circle.draw(svg);
            }
            for (int i = 0; i < shapes.get("small_square"); i++) {
                Shape square = new PositionedShape(factory.create(ss), rnd.nextInt(401), rnd.nextInt(401));
                square.draw(svg);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not create SVG file.");
        } catch (Exception e) {
            System.out.println("Something happened.");
        }
    }
}
