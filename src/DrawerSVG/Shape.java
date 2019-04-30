package DrawerSVG;

import java.util.List;

public interface Shape {
    List<Tag> getTags();

    default void draw(SVG svg) {
        for (Tag t: this.getTags())
            svg.addTag(t);
    }
}
