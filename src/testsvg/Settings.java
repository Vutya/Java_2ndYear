package testsvg;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

public class Settings {
    private String randSeed;
    private String background;
    private int width;
    private int height;
    private Properties props;

    public Settings() {
        Properties p = new Properties();
        try {
            p.load(new InputStreamReader(
                    new FileInputStream("test.properties"),
                    StandardCharsets.UTF_8
            ));
            this.props = p;
            this.randSeed = p.getProperty("rand_seed");
            this.background = p.getProperty("background");
            this.width = Integer.parseInt(p.getProperty("width"));
            this.height = Integer.parseInt(p.getProperty("height"));
        } catch (IOException e) {
            System.out.println("Properties are not found.");
            this.randSeed = "auto";
            this.background = "#FFFFFF";
            this.width = 400;
            this.height = 400;
        } catch (NumberFormatException e) {
            System.out.println("Something is not a number.");
        }
    }

    public String getBackground() {
        return background;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Optional<Long> getRandSeed() {
        if (randSeed.equals("auto"))
            return Optional.empty();
        return Optional.of(Long.parseLong(this.randSeed));
    }

    public String getShapeDescription(String figName) {
        return this.props.getProperty("shape." + figName);
    }

    public HashMap<String, Integer> getShapesWithCount() {
        String str = this.props.getProperty("draw");
        return (HashMap<String, Integer>) Arrays.stream(str.split(" "))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(e -> e[0], e -> Integer.parseInt(e[1])));
    }
}
