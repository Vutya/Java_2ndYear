package drawerSVG;

import java.util.Collections;
import java.util.List;

public class SmallSquare implements Shape {
    @Override
    public List<Tag> getTags() {
        Tag squareTag = new Tag("rect");
        squareTag.set("width", "20");
        squareTag.set("height", "20");
        squareTag.set("x", "-10");
        squareTag.set("y", "-10");

        return Collections.singletonList(squareTag);
    }
}
