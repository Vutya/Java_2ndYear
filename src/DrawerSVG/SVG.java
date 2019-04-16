package DrawerSVG;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.stream.Collectors;

class SVG implements AutoCloseable {
    private PrintStream out;
    private String indent = "\t";

    SVG(String filename, int width, int height) throws FileNotFoundException {
        out = new PrintStream(filename);
        out.println(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\">", width, height));
    }

    void addTag(Tag tag) {
        String attrs = tag.getProperties().entrySet()
                .stream()
                .map(entry -> String.format(" %s=\"%s\"", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(" "));
        switch (tag.getType()) {
            case OPEN_AND_CLOSE:
                out.println(indent + "<" + tag.getName() + attrs + "/>");
                break;
            case OPEN:
                out.println(indent + "<" + tag.getName() + attrs + ">");
                indent += "\t";
                break;
            case CLOSE:
                indent = indent.substring(0, indent.length() - 1);
                out.println(indent + "</" + tag.getName() + ">");
                break;
        }
    }

    @Override
    public void close() {
        out.println("</svg>");
        out.close();
    }
}
