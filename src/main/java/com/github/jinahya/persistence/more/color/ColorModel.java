package com.github.jinahya.persistence.more.color;

import com.github.jinahya.persistence.more.Mapped;

/**
 * An interface for mapping specific <em>color model</em>.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
interface ColorModel<T extends ColorModel<T>>
        extends Mapped<T> {

    /**
     * Sets color components on specified array.
     *
     * @param components the array on which color components are set; may be {@code null}.
     * @return given {@code components} or, when {@code null} specified, a newly created array of color components; not
     * {@code null}.
     */
    float[] getComponents(float[] components);

    /**
     * Sets value from specified color components.
     *
     * @param components the color components.
     */
    void setComponents(float[] components);

//    default float[] getCieXyzComponents(final ColorSpace colorSpace) {
//        Objects.requireNonNull(colorSpace, "colorSpace is null");
//        return colorSpace.toCIEXYZ(getComponents(null));
//    }
//
//    default float[] getTargetComponents(final ColorSpace sourceColorSpace, final ColorSpace targetColorSpace) {
//        Objects.requireNonNull(sourceColorSpace, "sourceColorSpace is null");
//        Objects.requireNonNull(targetColorSpace, "targetColorSpace is null");
//        return targetColorSpace.fromCIEXYZ(getCieXyzComponents(sourceColorSpace));
//    }
}
