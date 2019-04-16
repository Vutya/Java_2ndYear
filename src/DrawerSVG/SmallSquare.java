package DrawerSVG;

import java.util.ArrayList;
import java.util.List;

public class SmallSquare implements Shape {
    @Override
    public List<Tag> getTags() {
        List<Tag> tagList = new ArrayList<>();

        Tag squareTag = new Tag("rect");
        squareTag.set("width", "20");
        squareTag.set("height", "20");
        squareTag.set("x", "-10");
        squareTag.set("y", "-10");
        tagList.add(squareTag);

        return tagList;
    }
}
