package dev.poire.buzz4j;

import java.util.Objects;

/**
 * Represents a glyph with drawing information returned from HarfBuzz.
 */
public final class ShapeGlyph {
    private final int glyphId;
    private final int advanceX;
    private final int advanceY;
    private final int offsetX;
    private final int offsetY;

    ShapeGlyph(int glyphId, int advanceX, int advanceY, int offsetX, int offsetY) {
        this.glyphId = glyphId;
        this.advanceX = advanceX;
        this.advanceY = advanceY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    /**
     * The integer ID of the glpyh.
     * @return the integer ID
     */
    public int glyphId() {
        return glyphId;
    }

    /**
     * By how much the glyph advances the text shape on the X axis
     * @return the advance on X axis
     */
    public int advanceX() {
        return advanceX;
    }

    /**
     * By how much the glyph advances the text shape on the Y axis
     * @return the advance on Y axis
     */
    public int advanceY() {
        return advanceY;
    }

    /**
     * By how much the glyph should be offseted on the X axis
     * @return the offset on X axis
     */
    public int offsetX() {
        return offsetX;
    }

    /**
     * By how much the glyph should be offseted on the Y axis
     * @return the offset on Y axis
     */
    public int offsetY() {
        return offsetY;
    }

    /**
     * The effective width of the glyph.
     * @return the sum of offset and advance
     */
    public int effectiveWidth() {
        return advanceX + offsetX;
    }

    /**
     * The effective height of the glyph.
     * @return the sum of offset and advance
     */
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
