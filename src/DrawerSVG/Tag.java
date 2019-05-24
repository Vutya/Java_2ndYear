package DrawerSVG;

import java.util.HashMap;

class Tag {
    private String name;
    private TagType type;
    private HashMap<String, String> properties = new HashMap<>();

    Tag(String tagname) {
        this.name = tagname;
        this.type = TagType.OPEN_AND_CLOSE;
    }

    Tag(String tagname, TagType tagtype) {
        this.name = tagname;
        this.type = tagtype;
    }

    void set(String property, String value) {
        this.properties.put(property, value);
    }

    public String getName() {
        return name;
    }

    public TagType getType() {
        return type;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }
}
