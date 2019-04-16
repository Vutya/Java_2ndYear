package DrawerSVG;

import java.util.ArrayList;
import java.util.List;

public class RedCircle implements Shape {
    @Override
    public List<Tag> getTags() {
        List<Tag> tagList = new ArrayList<>();

        Tag circleTag = new Tag("circle");
        circleTag.set("r", "60");
        circleTag.set("style", "stroke:#000000; fill: #ff0000");
        tagList.add(circleTag);

        return tagList;
    }
}
