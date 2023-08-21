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

        Assertions.assertArrayEquals(new ShapeGlyph[]{
                new ShapeGlyph(5157, 486, 0, 0, 0),
                new ShapeGlyph(5380, 373, 0, 0, 0),
                new ShapeGlyph(3977, 269, 0, 0, 0),
                new ShapeGlyph(4860, 396, 0, 0, 0),
                new ShapeGlyph(3887, 521, 0, 0, 0),
                new ShapeGlyph(4543, 260, 0, 0, 0),
                new ShapeGlyph(3921, 238, 0, 0, 0)
        }, outputCombined);

        ShapeGlyph[] outputSplit = Arrays.stream(input.split("")).flatMap(c -> {
            try {
                return Arrays.stream(HarfBuzz.shapeString(font(), c));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toArray(ShapeGlyph[]::new);

        Assertions.assertArrayEquals(new ShapeGlyph[]{
                new ShapeGlyph(3921, 238, 0, 0, 0),
                new ShapeGlyph(4541, 695, 0, 0, 0),
                new ShapeGlyph(3883, 507, 0, 0, 0),
                new ShapeGlyph(4859, 367, 0, 0, 0),
                new ShapeGlyph(3975, 993, 0, 0, 0),
                new ShapeGlyph(5376, 760, 0, 0, 0),
                new ShapeGlyph(5156, 405, 0, 0, 0)
        }, outputSplit);
    }
}
