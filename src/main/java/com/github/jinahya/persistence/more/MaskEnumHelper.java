package com.github.jinahya.persistence.more;

import java.util.Objects;

final class MaskEnumHelper {

    static final class OfLong {

        static <E extends MaskEnum.OfLong> E valueOfMask(final Class<? extends E> enumClass, final long mask) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            if (!enumClass.isEnum()) {
                throw new IllegalArgumentException("not declared as an enum: " + enumClass);
            }
            for (final E enumConstant : enumClass.getEnumConstants()) {
                if (enumConstant.mask() == mask) {
                    return enumConstant;
                }
            }
            return null;
        }

        static <E extends MaskEnum.OfLong> long maskOfAll(final Class<? extends E> enumClass) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            if (!enumClass.isEnum()) {
                throw new IllegalArgumentException("not declared as an enum: " + enumClass);
            }
            long maskOfAll = __BitMask.OfLong.VALUE_OF_NONE;
            for (final E enumConstant : enumClass.getEnumConstants()) {
                maskOfAll |= enumConstant.mask();
            }
            return maskOfAll;
        }

        private OfLong() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static <E extends MaskEnum> E valueOfMask(final Class<? extends E> enumClass, final int mask) {
        Objects.requireNonNull(enumClass, "enumClass is null");
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException("not declared as an enum: " + enumClass);
        }
        for (final E enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.mask() == mask) {
                return enumConstant;
            }
        }
        return null;
    }

    static <E extends MaskEnum> int maskOfAll(final Class<? extends E> enumClass) {
        Objects.requireNonNull(enumClass, "enumClass is null");
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException("not declared as an enum: " + enumClass);
        }
        int maskOfAll = __BitMask.VALUE_OF_NONE;
        for (final E enumConstant : enumClass.getEnumConstants()) {
            maskOfAll |= enumConstant.mask();
        }
        return maskOfAll;
    }

    private MaskEnumHelper() {
        throw new AssertionError("instantiation is not allowed");
    }
}
