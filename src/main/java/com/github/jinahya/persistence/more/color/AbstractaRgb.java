package com.github.jinahya.persistence.more.color;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.io.Serial;
import java.util.Objects;
import java.util.regex.Matcher;

@MappedSuperclass
public abstract class AbstractaRgb<T extends AbstractaRgb<T>>
        implements Rgb<T> {

    @Serial
    private static final long serialVersionUID = -6080855026488682786L;

    public static final String COLUMN_NAME_VALUE_ = "value_";

    protected int getValue_() {
        return value_;
    }

    protected void setValue_(final int value_) {
        this.value_ = value_;
    }

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_VALUE_, nullable = true)
    private int value_ = RgbConstants.FULLY_OPAQUE << RgbConstants.SHIFT_ALPHA;

    /**
     * Formats this value as a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color
     * notation</a> consists of 3 hexadecimal digits.
     *
     * @return a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color notation</a>
     * consists of 3 hexadecimal digits
     */
    public String getValueAsCssColorHexNotation3() {
        return RgbUtils.toCssColorHexNotation3(getValue_());
    }

    public void setValueFromCssColorHexNotation3(final String hexNotation3) {
        Objects.requireNonNull(hexNotation3, "hexNotation3 is null");
        final Matcher matcher = RgbConstants.PATTERN_CSS_COLOR_HEX_NOTATION_3.matcher(hexNotation3);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid hexNotation3: " + hexNotation3);
        }
        final String group = matcher.group(0);
        final char r = group.charAt(0);
        final char g = group.charAt(1);
        final char b = group.charAt(2);
        setValueFromCssColorHexNotation6(String.valueOf(new char[]{r, r, g, g, b, b}));
    }

    /**
     * Formats this value as a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color
     * notation</a> consists of 6 hexadecimal digits.
     *
     * @return a <a href="https://w3c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color notation</a>
     * consists of 6 hexadecimal digits
     */
    public String getValueAsCssColorHexNotation6() {
        return RgbUtils.toCssColorHexNotation6(getValue_());
    }

    public void setValueFromCssColorHexNotation6(final String hexNotation6) {
        Objects.requireNonNull(hexNotation6, "hexNotation6 is null");
        final Matcher matcher = RgbConstants.PATTERN_CSS_COLOR_HEX_NOTATION_6.matcher(hexNotation6);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid hexNotation6: " + hexNotation6);
        }
        final String group = matcher.group(0);
        setRed(Integer.parseInt(group.substring(0, 2), 16));
        setGreen(Integer.parseInt(group.substring(2, 4), 16));
        setBlue(Integer.parseInt(group.substring(4, 6), 16));
    }

    /**
     * Formats this value as a <a href="https://w4c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color
     * notation</a> consists of 4 hexadecimal digits.
     *
     * @return a <a href="https://w4c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color notation</a>
     * consists of 4 hexadecimal digits
     */
    public String getValueAsCssColorHexNotation4() {
        return RgbUtils.toCssColorHexNotation4(getValue_());
    }

    public void setValueFromCssColorHexNotation4(final String hexNotation4) {
        Objects.requireNonNull(hexNotation4, "hexNotation4 is null");
        final Matcher matcher = RgbConstants.PATTERN_CSS_COLOR_HEX_NOTATION_4.matcher(hexNotation4);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid hexNotation4: " + hexNotation4);
        }
        final String group = matcher.group(0);
        final char r = group.charAt(0);
        final char g = group.charAt(1);
        final char b = group.charAt(2);
        final char a = group.charAt(3);
        setValueFromCssColorHexNotation8(String.valueOf(new char[]{r, r, g, g, b, b, a, a}));
    }

    /**
     * Replaces current value from a value parsed from specified <a
     * href="https://w4c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color notation</a> consists of 8
     * hexadecimal digits.
     *
     * @return a <a href="https://w4c.github.io/csswg-drafts/css-color/#hex-notation">CSS hex color notation</a>
     * consists of 8 hexadecimal digits
     */
    public String getValueAsCssColorHexNotation8() {
        return RgbUtils.toCssColorHexNotation8(getValue_());
    }

    public void setValueFromCssColorHexNotation8(final String hexNotation8) {
        Objects.requireNonNull(hexNotation8, "hexNotation8 is null");
        final Matcher matcher = RgbConstants.PATTERN_CSS_COLOR_HEX_NOTATION_8.matcher(hexNotation8);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid hexNotation8: " + hexNotation8);
        }
        final String group = matcher.group(0);
        setRed(Integer.parseInt(group.substring(0, 2), 16));
        setGreen(Integer.parseInt(group.substring(2, 4), 16));
        setBlue(Integer.parseInt(group.substring(4, 6), 16));
        setAlpha(Integer.parseInt(group.substring(6, 8), 16));
    }

    /**
     * Replaces current value with a value parsed from specified hex notation.
     *
     * @param hexNotation the nex notation string to parse; either
     *                    {@link #setValueFromCssColorHexNotation3(String) hexNotation3},
     *                    {@link #setValueFromCssColorHexNotation6(String) nexNotation6},
     *                    {@link #setValueFromCssColorHexNotation4(String) hexNotation4}, or
     *                    {@link #setValueFromCssColorHexNotation8(String) hexNotation8}.
     */
    public void setValueFromCssColorHexNotation(final String hexNotation) {
        Objects.requireNonNull(hexNotation, "hexNotation is null");
        try {
            setValueFromCssColorHexNotation8(hexNotation);
            return;
        } catch (final IllegalArgumentException iae) {
            // empty
        }
        try {
            setValueFromCssColorHexNotation4(hexNotation);
            return;
        } catch (final IllegalArgumentException iae) {
            // empty
        }
        try {
            setValueFromCssColorHexNotation6(hexNotation);
            return;
        } catch (final IllegalArgumentException iae) {
            // empty
        }
        try {
            setValueFromCssColorHexNotation3(hexNotation);
            return;
        } catch (final IllegalArgumentException iae) {
            // empty
        }
        throw new IllegalArgumentException("invalid hexNotation: " + hexNotation);
    }
}
