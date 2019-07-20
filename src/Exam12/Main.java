import TurtleGraphics.Pen;
import TurtleGraphics.StandardPen;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        // Task 1
        StringTransformer fractal = (String s) -> (s.replace("F", "F+F--F+F"));
        System.out.println(fractal.transform("F"));
        System.out.println(fractal.transform("F", 2));
        System.out.println(fractal.transform("F", 2).equals("F+F--F+F+F+F--F+F--F+F--F+F+F+F--F+F"));

        try {
            Files.writeString(Path.of("./fractal.txt"), fractal.transform("F", 10));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Task 2
        String rect = "<rect width=\"100\" height=\"100\"/>";
        StringTransformer napkin = (String s) -> ("<g transform=\"scale(0.333333333333 0.3333333333333)\">\n" +
                String.format("<g transform=\"translate(0, 0)\">%s</g>\n", s) +
                String.format("<g transform=\"translate(100, 0)\">%s</g>\n", s) +
                String.format("<g transform=\"translate(200, 0)\">%s</g>\n", s) +
                String.format("<g transform=\"translate(0, 100)\">%s</g>\n", s) +
                String.format("<g transform=\"translate(200, 100)\">%s</g>\n", s) +
                String.format("<g transform=\"translate(0, 200)\">%s</g>\n", s) +
                String.format("<g transform=\"translate(100, 200)\">%s</g>\n", s) +
                String.format("<g transform=\"translate(200, 200)\">%s</g>\n", s) +
                "</g>\n"
        );
        try {
            Files.writeString(Path.of("./napkin.svg"),
                    String.format(
                            "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"100\" height=\"100\">" +
                                    "<rect width=\"100\" height=\"100\" fill=\"#FFFFFF\"/>\n%s</svg>",
                            napkin.transform(rect, 5)
                    ));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Task 3
        try {
            String instruction = Files.readString(Path.of("./fractal.txt"));
            Pen turtle = new StandardPen();
            turtle.up();
            turtle.turn(90);
            turtle.move(-575);
            turtle.down();
            turtle.setColor(Color.BLACK);
            for (char ch: instruction.toCharArray())
                switch (ch) {
                    case 'F':
                        turtle.move(0.02);
                        break;
                    case '+':
                        turtle.turn(60);
                        break;
                    case '-':
                        turtle.turn(-60);
                        break;
                }
            turtle.up();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
