package testsvg;

import java.util.stream.Collectors;

class SVG {
    private StringBuffer out;
    private String indent = "\t";

    SVG(){
        Settings settings = new Settings();
        int width = settings.getWidth();
        int height = settings.getHeight();
        String bg_color = settings.getBackground();

        out = new StringBuffer();
        out.append(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" " +
                "width=\"%d\" height=\"%d\">", width, height));
        out.append(String.format("\t<rect width=\"%d\" height=\"%d\" fill=\"%s\"/>", width, height, bg_color));
    }

    void addTag(Tag tag) {
        String attrs = tag.getProperties().entrySet()
                .stream()
                .map(entry -> String.format(" %s=\"%s\"", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(" "));
        switch (tag.getType()) {
            case OPEN_AND_CLOSE:
                out.append(String.format("%s<%s%s/>", indent, tag.getName(), attrs));
                break;
            case OPEN:
                out.append(String.format("%s<%s%s>", indent, tag.getName(), attrs));
                indent += "\t";
                break;
            case CLOSE:
                indent = indent.substring(0, indent.length() - 1);
                out.append(String.format("%s</%s>", indent, tag.getName()));
                break;
        }
    }

    public void close() {
        out.append("</svg>");
    }

    public String getContent() {
        return out.toString();
    }
}
