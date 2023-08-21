package dev.poire.buzz4j;

import java.util.Objects;

public final class ShapeGlyph {
    private final int glyphId;
    private final int advanceX;
    private final int advanceY;
    private final int offsetX;
    private final int offsetY;

    public ShapeGlyph(int glyphId, int advanceX, int advanceY, int offsetX, int offsetY) {
        this.glyphId = glyphId;
        this.advanceX = advanceX;
        this.advanceY = advanceY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public int glyphId() {
        return glyphId;
    }

    public int advanceX() {
        return advanceX;
    }

    public int advanceY() {
        return advanceY;
    }

    public int offsetX() {
        return offsetX;
    }

    public int offsetY() {
        return offsetY;
    }

    public int effectiveWidth() {
        return advanceX + offsetX;
    }

    public int effectiveHeight() {
        return advanceY + offsetY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        ShapeGlyph that = (ShapeGlyph) obj;
        return this.glyphId == that.glyphId &&
                this.advanceX == that.advanceX &&
                this.advanceY == that.advanceY &&
                this.offsetX == that.offsetX &&
                this.offsetY == that.offsetY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(glyphId, advanceX, advanceY, offsetX, offsetY);
    }

    @Override
    public String toString() {
        return "ShapeGlyph[" +
                "glyphId=" + glyphId + ", " +
                "advanceX=" + advanceX + ", " +
                "advanceY=" + advanceY + ", " +
                "offsetX=" + offsetX + ", " +
                "offsetY=" + offsetY + ']';
    }

}
