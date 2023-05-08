package com.github.jinahya.persistence.more;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.io.Serializable;
import java.lang.reflect.Field;

public interface MaskEnum
        extends Serializable {

    interface OfLong
            extends Serializable {

        /**
         * Checks whether this mask is on specified face.
         *
         * @param face the face to check.
         * @return {@code true} if this mask is on the {@code face}; {@code false} otherwise.
         */
        default boolean isOn(final long face) {
            return __BitFace.OfLong.of(face)
                    .isWearing(__BitMask.OfLong.maskOfLong(mask()))
                    ;
        }

        /**
         * Puts this mask on to specified face.
         *
         * @param face the face to which this mask is put on.
         * @return new face with this mask on.
         */
        default long putOnTo(final long face) {
            return __BitFace.OfLong.of(face)
                    .putOn(__BitMask.OfLong.maskOfLong(mask()))
                    .value
                    ;
        }

        /**
         * Takes this mask off from specified face.
         *
         * @param face the face from which this mask is take off.
         * @return new face with this mask off.
         */
        default long takeOffFrom(final long face) {
            return __BitFace.OfLong.of(face)
                    .takeOff(__BitMask.OfLong.maskOfLong(mask()))
                    .value
                    ;
        }

        /**
         * Returns the mask value of this constant.
         *
         * @return the mask value of this constant.
         */
        @Max(MaskEnumConstants.OfLong.MAX_MASK)
        @Min(MaskEnumConstants.OfLong.MIN_MASK)
        default long mask() {
            try {
                final Field field = getClass().getDeclaredField(MaskEnumConstants.FIELD_NAME_MASK);
                if (!field.canAccess(null)) {
                    field.setAccessible(true);
                }
                assert field.getType() == long.class;
                final long value = field.getLong(this);
                return MaskEnums.OfLong.requireValidMask(value);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(
                        "failed to get '" + MaskEnumConstants.FIELD_NAME_MASK + "' field value of " + this,
                        roe
                );
            }
        }
    }

    /**
     * Checks whether this mask is on specified face.
     *
     * @param face the face to check.
     * @return {@code true} if this mask is on the {@code face}; {@code false} otherwise.
     */
    default boolean isOn(final int face) {
        return __BitFace.of(face)
                .isWearing(__BitMask.maskOf(mask()))
                ;
    }

    /**
     * Puts this mask on to specified face.
     *
     * @param face the face to which this mask is put on.
     * @return new face with this mask on.
     */
    default int putOnTo(final int face) {
        return __BitFace.of(face)
                .putOn(__BitMask.maskOf(mask()))
                .getValue()
                ;
    }

    /**
     * Takes this mask off from specified face.
     *
     * @param face the face from which this mask is take off.
     * @return new face with this mask off.
     */
    default int takeOffFrom(final int face) {
        return __BitFace.of(face)
                .takeOff(__BitMask.maskOf(mask()))
                .getValue();
    }

    /**
     * Returns the mask value of this constant.
     *
     * @return the mask value of this constant.
     */
    @Max(MaskEnumConstants.MAX_MASK)
    @Min(MaskEnumConstants.MIN_MASK)
    default int mask() {
        try {
            final Field field = getClass().getDeclaredField(MaskEnumConstants.FIELD_NAME_MASK);
            if (!field.canAccess(null)) {
                field.setAccessible(true);
            }
            assert field.getType() == int.class;
            final int value = field.getInt(this);
            return MaskEnums.requireValidMask(value);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(
                    "failed to get '" + MaskEnumConstants.FIELD_NAME_MASK + "' field value of " + this,
                    roe
            );
        }
    }
}
