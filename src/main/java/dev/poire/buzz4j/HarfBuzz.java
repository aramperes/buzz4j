package dev.poire.buzz4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HarfBuzz {

    static {
        System.loadLibrary("png16-16");
        System.loadLibrary("freetype-6");
        System.loadLibrary("harfbuzz");
        System.loadLibrary("buzz4jni");
    }

    public static String shapeString(Path fontPath, String text) throws IOException {
        if (fontPath == null || !Files.isRegularFile(fontPath))
            throw new IOException("Font path provided is not a file: %s".formatted(fontPath));

        if (text == null || text.isEmpty())
            return "";

        final String[] glyphs = shapeStringGlyphs(fontPath.toString(), text);

        // TODO: Convert each glyph to Unicode
        return String.join(",", glyphs);
    }

    private static native String[] shapeStringGlyphs(String fontPath, String text);
}
