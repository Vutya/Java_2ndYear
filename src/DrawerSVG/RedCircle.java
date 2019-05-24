package DrawerSVG;

import java.util.Collections;
import java.util.List;

public class RedCircle implements Shape {
    @Override
    public List<Tag> getTags() {
        Tag circleTag = new Tag("circle");
        circleTag.set("r", "20");
        circleTag.set("style", "stroke:#000000; fill: #ff0000");

        return Collections.singletonList(circleTag);
    }
}
