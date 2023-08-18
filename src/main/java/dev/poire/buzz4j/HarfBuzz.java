package dev.poire.buzz4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HarfBuzz {

    private static final int GLYPH_LENGTH = 5;

    static {
        if (System.getProperty("os.name") != null
                && System.getProperty("os.name").startsWith("Windows")) {
            // Libraries are not assumed to be in PATH on Windows.
            System.loadLibrary("png16-16");
            System.loadLibrary("freetype-6");
        }

        System.loadLibrary("harfbuzz");
        System.loadLibrary("buzz4j");
    }

    public static ShapeGlyph[] shapeString(Path fontPath, String text) throws IOException {
        if (fontPath == null || !Files.isRegularFile(fontPath))
            throw new IOException(String.format("Font path provided is not a file: %s", fontPath));

        if (text == null || text.isEmpty())
            return new ShapeGlyph[0];

        final int[] glyphs = shapeStringGlyphs(fontPath.toString(), text);

        final ShapeGlyph[] converted = new ShapeGlyph[glyphs.length / GLYPH_LENGTH];
        for (int i = 0; i < converted.length; i++) {
            converted[i] = new ShapeGlyph(
                    glyphs[i * GLYPH_LENGTH],
                    glyphs[i * GLYPH_LENGTH + 1],
                    glyphs[i * GLYPH_LENGTH + 2],
                    glyphs[i * GLYPH_LENGTH + 3],
                    glyphs[i * GLYPH_LENGTH + 4]
            );
        }

        return converted;
    }

    private static native int[] shapeStringGlyphs(String fontPath, String text);
}
