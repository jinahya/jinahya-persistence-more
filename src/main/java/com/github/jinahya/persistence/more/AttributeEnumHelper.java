package com.github.jinahya.persistence.more;

import java.util.Objects;

final class AttributeEnumHelper {

    static final class OfInt {

        static <E extends AttributeEnum.OfInt> E valueOfAttribute(final Class<? extends E> enumClass,
                                                                  final int attributeValue) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            if (!enumClass.isEnum()) {
                throw new IllegalArgumentException("not declared as an enum: " + enumClass);
            }
            for (final E enumConstant : enumClass.getEnumConstants()) {
                if (enumConstant.attributeAsInt() == attributeValue) {
                    return enumConstant;
                }
            }
            return null;
        }

        private OfInt() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static final class OfLong {

        static <E extends AttributeEnum.OfLong> E valueOfAttribute(final Class<? extends E> enumClass,
                                                                   final long attributeValue) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            if (!enumClass.isEnum()) {
                throw new IllegalArgumentException("not declared as an enum: " + enumClass);
            }
            for (final E enumConstant : enumClass.getEnumConstants()) {
                if (enumConstant.attributeAsLong() == attributeValue) {
                    return enumConstant;
                }
            }
            return null;
        }

        private OfLong() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    /**
     * Finds an enum constants from specific class whose {@link AttributeEnum#attribute() attribute} matches to
     * specified value.
     *
     * @param enumClass      the enum class; should be an {@link Class#isEnum() enum} class.
     * @param attributeValue the attribute value to match.
     * @param <E>            enum type parameter
     * @param <A>            attribute type parameter
     * @return found enum constants; {@code null} if not found.
     */
    static <E extends AttributeEnum<A>, A> E valueOfAttribute(final Class<? extends E> enumClass,
                                                              final A attributeValue) {
        Objects.requireNonNull(enumClass, "enumClass is null");
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException("not declared as an enum: " + enumClass);
        }
        Objects.requireNonNull(attributeValue, "attributeValue is null");
        for (final E enumConstant : enumClass.getEnumConstants()) {
            if (Objects.equals(enumConstant.attribute(), attributeValue)) {
                return enumConstant;
            }
        }
        return null;
    }

    private AttributeEnumHelper() {
        throw new AssertionError("instantiation is not allowed");
    }
}
