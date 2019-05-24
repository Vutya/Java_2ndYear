import testsvg.*;

import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SVGTest {
    @DataProvider
    public static Object[][] testTagsData() {
        return new Object [][] {
                {"<svg"}, {"</svg>"},
                {"<g"}, {"</g>"},
                {"<rect"}, {"<circle"},
                {"/>"}, {"transform="},
                {"width="}, {"height="},
                {"r="}, {"x="}, {"y="}
        };
    }

    @Test(dataProvider = "testTagsData")
    public void test1(String s) {
        assertTrue(DrawSVG.createSVG().contains(s));
    }
}
