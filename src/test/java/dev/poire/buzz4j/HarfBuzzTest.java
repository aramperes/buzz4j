package dev.poire.buzz4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;

public class HarfBuzzTest {

    private static Path font() throws Exception {
        URL resource = HarfBuzzTest.class.getResource("/GoNotoCurrent-Regular.ttf");
        if (resource == null)
            throw new IOException("No resource for font");

        return new File(resource.toURI()).toPath();
    }

    @Test
    public void testShapeString() throws Exception {
        String input = "whatever";
        ShapeGlyph[] output = HarfBuzz.shapeString(font(), input);
        Assertions.assertArrayEquals(new ShapeGlyph[]{
                new ShapeGlyph(90, 786, 0, 0, 0),
                new ShapeGlyph(75, 618, 0, 0, 0),
                new ShapeGlyph(68, 561, 0, 0, 0),
                new ShapeGlyph(87, 361, 0, 0, 0),
                new ShapeGlyph(72, 544, 0, 0, 0),
                new ShapeGlyph(89, 508, 0, 0, 0),
                new ShapeGlyph(72, 564, 0, 0, 0),
                new ShapeGlyph(85, 413, 0, 0, 0),
        }, output);
    }

    @Test
    public void testShapeArabic() throws Exception {
        String input = "العربية";
        ShapeGlyph[] outputCombined = HarfBuzz.shapeString(font(), input);
        int sumCombined = Arrays.stream(outputCombined).mapToInt(ShapeGlyph::effectiveWidth).sum();
        int sumSplit = Arrays.stream(input.split("")).flatMap(c -> {
            try {
                return Arrays.stream(HarfBuzz.shapeString(font(), c));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).mapToInt(ShapeGlyph::effectiveWidth).sum();

        Assertions.assertNotEquals(sumCombined, sumSplit, "Shaping Arabic word should result in narrower shape");
        Assertions.assertEquals(9109, sumCombined);
        Assertions.assertEquals(9179, sumSplit);
    }
}
