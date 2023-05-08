package com.github.jinahya.persistence.more.color;

final class RgbUtils {

    static int getRgb(final int r, final int g, final int b) {
        return ((r & RgbConstants.FULLY_OPAQUE) << RgbConstants.SHIFT_RED) |
               ((g & RgbConstants.FULLY_OPAQUE) << RgbConstants.SHIFT_GREEN) |
               (b & RgbConstants.FULLY_OPAQUE);
    }

    static int getRgba(final int r, final int g, final int b, final int a) {
        return getArgb(a, r, g, b);
    }

    static int getArgb(final int a, final int r, final int g, final int b) {
        return ((a & RgbConstants.FULLY_OPAQUE) << RgbConstants.SHIFT_ALPHA) | getRgb(r, g, b);
    }

    static int getAlpha(final int argb) {
        return (argb >> RgbConstants.SHIFT_ALPHA) & RgbConstants.FULLY_OPAQUE;
    }

    static int setAlpha(final int argb, final int a) {
        return (argb & ~RgbConstants.MASK_ALPHA) | ((a & RgbConstants.FULLY_OPAQUE) << RgbConstants.SHIFT_ALPHA);
    }

    static int getRed(final int argb) {
        return (argb >> RgbConstants.SHIFT_RED) & RgbConstants.FULLY_OPAQUE;
    }

    static int setRed(final int argb, final int r) {
        return (argb & ~RgbConstants.MASK_RED) | ((r & RgbConstants.FULLY_OPAQUE) << RgbConstants.SHIFT_RED);
    }

    static int getGreen(final int argb) {
        return (argb >> RgbConstants.SHIFT_GREEN) & RgbConstants.FULLY_OPAQUE;
    }

    static int setGreen(final int argb, final int g) {
        return (argb & ~RgbConstants.MASK_GREEN) | ((g & RgbConstants.FULLY_OPAQUE) << RgbConstants.SHIFT_GREEN);
    }

    static int getBlue(final int argb) {
        return argb & RgbConstants.FULLY_OPAQUE;
    }

    static int setBlue(final int argb, final int b) {
        return (argb & ~RgbConstants.MASK_BLUE) | (b & RgbConstants.FULLY_OPAQUE);
    }

    /**
     * Formats specified values as a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color
     * notation</a> consists of 3 hexadecimal digits.
     *
     * @param r a value of {@code red} color.
     * @param g a value of {@code green} color.
     * @param b a value of {@code blue} color.
     * @return a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color notation</a>
     * consists of 3 hexadecimal digits
     */
    static String toCssColorHexNotation3(final int r, final int g, final int b) {
        return String.format("%1$x%2$x%3$x",
                             (r >> RgbConstants.SHIFT_HALF) & RgbConstants.FULLY_OPAQUE_HALF,
                             (g >> RgbConstants.SHIFT_HALF) & RgbConstants.FULLY_OPAQUE_HALF,
                             (b >> RgbConstants.SHIFT_HALF) & RgbConstants.FULLY_OPAQUE_HALF
        );
    }

    static String toCssColorHexNotation3(final int _rgb) {
        return toCssColorHexNotation3(getRed(_rgb), getGreen(_rgb), getBlue(_rgb));
    }

    /**
     * Formats specified values as a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color
     * notation</a> consists of 6 hexadecimal digits.
     *
     * @param r a value of {@code red} component.
     * @param g a value of {@code green} component.
     * @param b a value of {@code blue} component.
     * @return a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color notation</a>
     * consists of 6 hexadecimal digits
     */
    static String toCssColorHexNotation6(final int r, final int g, final int b) {
        return String.format("%1$02x%2$02x%3$02x", r, g, b);
    }

    static String toCssColorHexNotation6(final int _rgb) {
        return toCssColorHexNotation6(getRed(_rgb), getGreen(_rgb), getBlue(_rgb));
    }

    static String toCssColorHexNotation4(final int argb) {
        return toCssColorHexNotation3(argb) + String.format("%x", getAlpha(argb) >> RgbConstants.SHIFT_HALF);
    }

    /**
     * Formats specified values as a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color
     * notation</a> consists of 4 hexadecimal digits.
     *
     * @param r a value of {@code red} color.
     * @param g a value of {@code green} color.
     * @param b a value of {@code blue} color.
     * @param a a value of {@code alpha} channel.
     * @return a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color notation</a>
     * consists of 4 hexadecimal digits
     */
    static String toCssColorHexNotation4(final int r, final int g, final int b, final int a) {
        return toCssColorHexNotation4(getArgb(a, r, g, b));
    }

    static String toCssColorHexNotation4(final int r, final int g, final int b) {
        return toCssColorHexNotation4(r, g, b, RgbConstants.FULLY_OPAQUE);
    }

    static String toCssColorHexNotation8(final int argb) {
        return toCssColorHexNotation6(argb) + String.format("%1$02x", getAlpha(argb));
    }

    /**
     * Formats specified values as a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color
     * notation</a> consists of 8 hexadecimal digits.
     *
     * @param r a value of {@code red} color.
     * @param g a value of {@code green} color.
     * @param b a value of {@code blue} color.
     * @param a a value of {@code alpha} channel.
     * @return a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color notation</a>
     * consists of 8 hexadecimal digits
     */
    static String toCssColorHexNotation8(final int r, final int g, final int b, final int a) {
        return toCssColorHexNotation8(getArgb(a, r, g, b));
    }

    static String toCssColorHexNotation8(final int r, final int g, final int b) {
        return toCssColorHexNotation8(r, g, b, RgbConstants.FULLY_OPAQUE);
    }

    private RgbUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
