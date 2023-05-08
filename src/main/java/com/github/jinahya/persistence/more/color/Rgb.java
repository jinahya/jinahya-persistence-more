package com.github.jinahya.persistence.more.color;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Objects;
import java.util.function.IntFunction;

/**
 * An interface for mapping color values in <span style='color:red'>R</span><span style='color:green'>G</span><span
 * style='color:blue'>B</span><span style='color:gray'>A</span> color model.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public interface Rgb<T extends Rgb<T>>
        extends ColorModel<T> {

    /**
     * Sets color components of <span style='color:red'>red</span>, <span style='color:green'>green</span>, <span
     * style='color:blue'>blue</span>, and <span style='color:gray'>alpha</span>, in order, on specified array.
     *
     * @param components the array on which color components are set; may be {@code null} yet should be long enough for
     *                   <em>four</em> color components.
     * @return {@inheritDoc}
     */
    @Override
    default float[] getComponents(float[] components) {
        if (components == null) {
            return getComponents(new float[4]);
        }
        if (components.length < 4) {
            throw new IllegalArgumentException("components.length(" + components.length + ") < 4");
        }
        components[0] = getRed() / 255f;
        components[1] = getGreen() / 255f;
        components[2] = getBlue() / 255f;
        components[3] = getAlpha() / 255f;
        return components;
    }

    @Override
    default void setComponents(final float[] components) {
        Objects.requireNonNull(components, "components is null");
        if (components.length < 4) {
            throw new IllegalArgumentException("components.length(" + components.length + ") < 4");
        }
        setRed((int) (components[0] * 255f));
        setGreen((int) (components[1] * 255f));
        setBlue((int) (components[2] * 255f));
        setAlpha((int) (components[3] * 255f));
    }

    /**
     * Returns the value of the {@code red} component.
     *
     * @return the value of the {@code red} component; between {@code 0} and {@code 255}, both inclusive.
     */
    @Max(RgbConstants.FULLY_OPAQUE)
    @PositiveOrZero
    int getRed();

    /**
     * Replaces current value of the {@code red} component with specified value.
     *
     * @param red new value, for the {@code red} component, whose lower {@code 8} bit is used.
     */
    void setRed(@Max(RgbConstants.FULLY_OPAQUE) @PositiveOrZero int red);

    @Max(RgbConstants.FULLY_OPAQUE)
    @PositiveOrZero
    int getGreen();

    void setGreen(@Max(RgbConstants.FULLY_OPAQUE) @PositiveOrZero int green);

    @Max(RgbConstants.FULLY_OPAQUE)
    @PositiveOrZero
    int getBlue();

    void setBlue(@Max(RgbConstants.FULLY_OPAQUE) @PositiveOrZero int blue);

    @Max(RgbConstants.FULLY_OPAQUE)
    @PositiveOrZero
    int getAlpha();

    void setAlpha(@Max(RgbConstants.FULLY_OPAQUE) @PositiveOrZero int alpha);

    /**
     * Applies <span style='color:red'>red</span>, <span style='color:green'>green</span>, and <span
     * style='color:blue'>blue</span>, in order, to specified function, and returns the result.
     * <pre>
     * applyArgb(<span style='color:red'>r</span> -> <span style='color:green'>g</span> -> <span style='color:blue'>b</span> -> null);
     * </pre>
     *
     * @param function the function to be applied.
     * @param <R>      result type parameter
     * @return result of the {@code function}.
     */
    default <R> R applyRgb(final IntFunction<? extends IntFunction<? extends IntFunction<? extends R>>> function) {
        Objects.requireNonNull(function, "function is null");
        return applyArgb(a -> function);
    }

    default int getRgb() {
        return applyRgb(r -> g -> b -> RgbUtils.getRgb(r, g, b));
    }

    default void setRgb(final int _rgb) {
        setRed(RgbUtils.getRed(_rgb));
        setGreen(RgbUtils.getGreen(_rgb));
        setBlue(RgbUtils.getBlue(_rgb));
    }

    /**
     * Applies <span style='color:red'>red</span>, <span style='color:green'>green</span>, <span
     * style='color:blue'>blue</span>, and <span style='color:gray'>alpha</span>, in order, to specified function, and
     * returns the result.
     * <pre>
     * applyArgb(<span style='color:red'>r</span> -> <span style='color:green'>g</span> -> <span style='color:blue'>b</span> -> <span style='color:gray'>a</span> -> null);
     * </pre>
     *
     * @param function the function to be applied.
     * @param <R>      result type parameter
     * @return result of the {@code function}.
     */
    default <R> R applyRgba(final IntFunction<? extends IntFunction<? extends IntFunction<? extends IntFunction<? extends R>>>> function) {
        Objects.requireNonNull(function, "function is null");
        return applyArgb(a -> r -> g -> b -> function.apply(r).apply(g).apply(b).apply(a));
    }

    /**
     * Applies <span style='color:gray'>alpha</span>, <span style='color:red'>red</span>, <span
     * style='color:green'>green</span>, and <span style='color:blue'>blue</span>, in order, to specified function, and
     * returns the result.
     * <pre>
     * applyArgb(<span style='color:gray'>a</span> -> <span style='color:red'>r</span> -> <span style='color:green'>g</span> -> <span style='color:blue'>b</span> -> null);
     * </pre>
     *
     * @param function the function to be applied.
     * @param <R>      result type parameter
     * @return result of the {@code function}.
     */
    default <R> R applyArgb(final IntFunction<? extends IntFunction<? extends IntFunction<? extends IntFunction<? extends R>>>> function) {
        Objects.requireNonNull(function, "function is null");
        return function
                .apply(getAlpha())
                .apply(getRed())
                .apply(getGreen())
                .apply(getBlue())
                ;
    }

    default int getArgb() {
        return applyArgb(a -> r -> g -> b -> RgbUtils.getArgb(a, r, g, b));
    }

    default void setArgb(final int argb) {
        setAlpha(RgbUtils.getAlpha(argb));
        setRgb(argb);
    }
}
