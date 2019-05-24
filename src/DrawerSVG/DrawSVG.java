package drawerSVG;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public class DrawSVG {
    public static void main(String[] args) {
        Optional<Long> randSeed = Settings.getInstance().getRandSeed();
        Random rnd = randSeed.map(Random::new).orElseGet(Random::new);

        ShapeFactory factory = new ShapeFactory();
        HashMap<String, Integer> shapes = Settings.getInstance().getShapesWithCount();

        try (SVG svg = new SVG("test.svg")) {
            shapes.forEach((shape, count) -> {
                //list of shapes, list.shuffle(rnd)
                try {
                    for (int i = 0; i < count; i++) {
                        String s = Settings.getInstance().getShapeDescription(shape);
                        Shape posShape = new PositionedShape(factory.create(s),
                                rnd.nextInt(Settings.getInstance().getWidth() + 1),
                                rnd.nextInt(Settings.getInstance().getHeight() + 1));
                        posShape.draw(svg);
                    }
                } catch (Exception e) {
                    System.out.println("Something has broken, when creating shapes.");
                }
            });
        } catch (FileNotFoundException e) {
            System.out.println("Could not create SVG file.");
        } catch (Exception e) {
            System.out.println("Something happened.");
        }
    }
}
