package testsvg;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public class DrawSVG {
    public static String createSVG() {
        Settings settings = new Settings();
        Optional<Long> randSeed = settings.getRandSeed();
        Random rnd = randSeed.map(Random::new).orElseGet(Random::new);

        ShapeFactory factory = new ShapeFactory();
        HashMap<String, Integer> shapes = settings.getShapesWithCount();

        SVG svg = new SVG();

        shapes.forEach((shape, count) -> {
            //list of shapes, list.shuffle(rnd)
            try {
                for (int i = 0; i < count; i++) {
                    String s = settings.getShapeDescription(shape);
                    Shape posShape = new PositionedShape(factory.create(s),
                            rnd.nextInt(settings.getWidth() + 1),
                            rnd.nextInt(settings.getHeight() + 1));
                    posShape.draw(svg);
                }
            } catch (Exception e) {
                System.out.println("Something has broken, when creating shapes.");
            }
        });
        svg.close();

        return svg.getContent();
    }
}
