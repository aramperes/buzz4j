package dev.poire.buzz4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HarfBuzz {

    static {
        System.loadLibrary("buzz4jni");
    }

    public static String shapeString(Path fontPath, String text) throws IOException {
        if (!Files.isRegularFile(fontPath))
            throw new IOException("Font path provided is not file: %s".formatted(fontPath));

        byte[] buffer = text.getBytes(StandardCharsets.UTF_8);
        shapeBuffer(fontPath.toString(), buffer);

        return new String(buffer, StandardCharsets.UTF_8);
    }

    private static native void shapeBuffer(String fontPath, byte[] buffer);
}
