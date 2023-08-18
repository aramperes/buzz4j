package dev.poire.buzz4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

public class HarfBuzzTest {

    private static Path font() throws Exception {
        URL resource = HarfBuzzTest.class.getResource("/GoNotoCurrent-Regular.ttf");
        if (resource == null)
            throw new IOException("No resource for font");

        return Path.of(resource.toURI());
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
}
