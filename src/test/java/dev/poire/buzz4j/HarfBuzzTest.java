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
        String output = HarfBuzz.shapeString(font(), input);
        Assertions.assertEquals("\u007Bhatever", output);
    }
}
