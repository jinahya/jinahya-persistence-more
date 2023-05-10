package com.github.jinahya.persistence.more;

import jakarta.validation.constraints.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An interface for mapping a column value to an enum type of specified attribute type.
 *
 * @param <A> attribute type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public interface AttributeEnum<A> {

    interface OfInt {

        /**
         * Finds the enum constant, from specified enum class, whose {@link #attributeAsInt() attribute} equals to
         * specified value.
         *
         * @param enumClass the enum class; not {@code null}.
         * @param attribute the attribute value to find.
         * @param <E>       enum type parameter
         * @return the enum constant for {@code attribute}.
         */
        static <E extends Enum<E> & OfInt> E valueOfAttribute(final Class<E> enumClass, final int attribute) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            return Optional.ofNullable(AttributeEnumHelper.OfInt.valueOfAttribute(enumClass, attribute))
                    .orElseThrow(() -> new IllegalArgumentException(
                            "no enum constant, in " + enumClass + ", for " + attribute));
        }

        @SafeVarargs
        static <E extends OfInt> E valueOfAttribute(final int attribute, final Class<? extends E> enumClass,
                                                    final Class<? extends E>... otherEnumClasses) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            Objects.requireNonNull(otherEnumClasses, "otherEnumClasses is null");
            return Stream.concat(Stream.of(enumClass), Stream.of(otherEnumClasses))
                    .map(ec -> AttributeEnumHelper.OfInt.valueOfAttribute(ec, attribute))
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "no enum constant, in either " + enumClass + " + nor in any of "
                            + Arrays.toString(otherEnumClasses) + ", for " + attribute));
        }

        /**
         * Returns the attribute value of this constant.
         *
         * @return the attribute value of this constant.
         * @apiNote Default implementation finds a field named as {@value AttributeEnumConstants#FIELD_NAME_ATTRIBUTE},
         * and returns its value {@link Field#getInt(Object) as an int}.
         */
        default int attributeAsInt() {
            try {
                final Field field = getClass().getDeclaredField(AttributeEnumConstants.FIELD_NAME_ATTRIBUTE);
                if (!field.canAccess(this)) {
                    field.setAccessible(true);
                }
                return field.getInt(this);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(
                        "failed to get the value of '" + AttributeEnumConstants.FIELD_NAME_ATTRIBUTE + "' field from "
                        + this,
                        roe
                );
            }
        }
    }

    interface OfLong {

        /**
         * Finds the enum constant of specified enum class whose {@code attribute} equals to specified value.
         *
         * @param enumClass the enum class; not {@code null}.
         * @param attribute the attribute value to match.
         * @param <E>       enum type parameter
         * @return the enum constant for {@code attribute}.
         */
        static <E extends Enum<E> & OfLong> E valueOfAttribute(final Class<E> enumClass, final long attribute) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            return Optional.ofNullable(AttributeEnumHelper.OfLong.valueOfAttribute(enumClass, attribute))
                    .orElseThrow(() -> new IllegalArgumentException(
                            "no enum constant, in " + enumClass + ", for " + attribute));
        }

        @SafeVarargs
        static <E extends OfLong> E valueOfAttribute(final long attribute, final Class<? extends E> enumClass,
                                                     final Class<? extends E>... otherEnumClasses) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            Objects.requireNonNull(otherEnumClasses, "otherEnumClasses is null");
            return Stream.concat(Stream.of(enumClass), Stream.of(otherEnumClasses))
                    .map(ec -> AttributeEnumHelper.OfLong.valueOfAttribute(ec, attribute))
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "no enum constant, in either " + enumClass + " + nor in any of "
                            + Arrays.toString(otherEnumClasses) + ", for " + attribute));
        }

        /**
         * Returns the attribute value of this constant.
         *
         * @return the attribute value of this constant.
         * @apiNote Default implementation finds a field named as {@value AttributeEnumConstants#FIELD_NAME_ATTRIBUTE},
         * and returns its value {@link Field#getLong(Object) as a long}.
         */
        default long attributeAsLong() {
            try {
                final Field field = getClass().getDeclaredField(AttributeEnumConstants.FIELD_NAME_ATTRIBUTE);
                if (!field.canAccess(this)) {
                    field.setAccessible(true);
                }
                return field.getLong(this);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(
                        "failed to get the value of '" + AttributeEnumConstants.FIELD_NAME_ATTRIBUTE + "' field from "
                        + this,
                        roe
                );
            }
        }
    }

    interface OfCharacter
            extends AttributeEnum<Character> {

        /**
         * Finds the enum constant, of specified enum class, whose {@code attribute} equals to specified value.
         *
         * @param enumClass the enum class; not {@code null}.
         * @param attribute the attribute value to match; not {@code null}.
         * @param <E>       enum type parameter
         * @return the enum constant for {@code attribute}.
         */
        static <E extends Enum<E> & OfCharacter> E valueOfAttribute(final Class<E> enumClass,
                                                                    final Character attribute) {
            return AttributeEnum.valueOfAttribute(enumClass, attribute);
        }

        @SafeVarargs
        static <E extends OfCharacter> E valueOfAttribute(final Character attribute, final Class<? extends E> enumClass,
                                                          final Class<? extends E>... otherEnumClasses) {
            return AttributeEnum.valueOfAttribute(attribute, enumClass, otherEnumClasses);
        }

        @Override
        default Character attribute() {
            try {
                final Field field = getClass().getDeclaredField(AttributeEnumConstants.FIELD_NAME_ATTRIBUTE);
                if (!field.canAccess(this)) {
                    field.setAccessible(true);
                }
                final Object value = field.get(this);
                if (value == null) {
                    return ((String) Enum.class.getMethod("name").invoke(this)).charAt(0);
                }
                if (value instanceof Character c) {
                    return c;
                }
                return Objects.toString(value).charAt(0);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(
                        "failed to get the value of '" + AttributeEnumConstants.FIELD_NAME_ATTRIBUTE + "' field from "
                        + this, roe
                );
            }
        }
    }

    interface OfString
            extends AttributeEnum<String> {

        /**
         * Finds the enum constant, of specified enum class, whose {@link AttributeEnum#attribute() attribute} equals to
         * specified value.
         *
         * @param enumClass the enum class; not {@code null}.
         * @param attribute the attribute value to match; not {@code null}.
         * @param <E>       enum type parameter
         * @return the enum constant for {@code attribute}.
         */
        static <E extends Enum<E> & OfString> E valueOfAttribute(final Class<E> enumClass, final String attribute) {
            return AttributeEnum.valueOfAttribute(enumClass, attribute);
        }

        @SafeVarargs
        static <E extends OfString> E valueOfAttribute(final String attribute, final Class<? extends E> enumClass,
                                                       final Class<? extends E>... otherEnumClasses) {
            return AttributeEnum.valueOfAttribute(attribute, enumClass, otherEnumClasses);
        }

        @Override
        default String attribute() {
            try {
                final Field field = getClass().getDeclaredField(AttributeEnumConstants.FIELD_NAME_ATTRIBUTE);
                if (!field.canAccess(this)) {
                    field.setAccessible(true);
                }
                final Object value = field.get(this);
                if (value == null) {
                    return (String) Enum.class.getMethod("name").invoke(this);
                }
                return Objects.toString(value);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(
                        "failed to get the value of '" + AttributeEnumConstants.FIELD_NAME_ATTRIBUTE + "' field from "
                        + this,
                        roe
                );
            }
        }
    }

    /**
     * Finds the enum constant of specified enum class whose {@link #attribute() attribute} matches to specified value.
     *
     * @param enumClass the enum class; not {@code null}.
     * @param attribute the attribute value to match; not {@code null}.
     * @param <E>       enum type parameter
     * @param <A>       attribute value type parameter
     * @return the enum constant for {@code attribute}.
     */
    static <E extends Enum<E> & AttributeEnum<A>, A> E valueOfAttribute(final Class<E> enumClass, final A attribute) {
        Objects.requireNonNull(enumClass, "enumClass is null");
        Objects.requireNonNull(attribute, "attribute is null");
        return Optional.ofNullable(AttributeEnumHelper.valueOfAttribute(enumClass, attribute))
                .orElseThrow(() -> new IllegalArgumentException(
                        "no enum constant, in " + enumClass + ", for " + attribute));
    }

    @SafeVarargs
    static <E extends AttributeEnum<A>, A> E valueOfAttribute(final A attribute, final Class<? extends E> enumClass,
                                                              final Class<? extends E>... otherEnumClasses) {
        Objects.requireNonNull(attribute, "attribute is null");
        Objects.requireNonNull(enumClass, "enumClass is null");
        Objects.requireNonNull(otherEnumClasses, "otherEnumClasses is null");
        return Stream.concat(Stream.of(enumClass), Stream.of(otherEnumClasses))
                .map(ec -> AttributeEnumHelper.valueOfAttribute(ec, attribute))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "no enum constant, in either " + enumClass + " + nor in any of "
                        + Arrays.toString(otherEnumClasses) + ", for " + attribute));
    }

//    @NonNull
//    @SafeVarargs
//    static <A> _AttributeEnum<?, A> valueOfAttribute(
//            @NonNull final A attributeValue, @NonNull final Class<? extends _AttributeEnum<?, A>> enumClass,
//            @NonNull final Class<? extends _AttributeEnum<?, A>>... otherEnumClasses) {
//        Objects.requireNonNull(attributeValue, _AttributeEnumConstants.MESSAGE_ATTRIBUTE_VALUE_IS_NULL);
//        Objects.requireNonNull(enumClass, _AttributeEnumConstants.MESSAGE_ENUM_CLASS_IS_NULL);
//        Objects.requireNonNull(otherEnumClasses, _AttributeEnumConstants.MESSAGE_OTHER_ENUM_CLASSES_IS_NULL);
//        return Stream.concat(Stream.of(enumClass), Stream.of(otherEnumClasses))
//                .map(ec -> _AttributeEnumHelper.valueOfAttribute(ec, attributeValue))
//                .filter(Objects::nonNull)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(
//                        "no enum constant, in either " + enumClass + " + nor in any of "
//                        + Arrays.toString(otherEnumClasses) + ", for " + attributeValue));
//    }

    static <E extends Enum<E> & AttributeEnum<?>> ResourceBundle getResourceBundle(final Class<E> enumClass,
                                                                                   final Locale locale) {
        Objects.requireNonNull(enumClass, "enumClass is null");
        Objects.requireNonNull(locale, "locale is null");
        final String baseName = enumClass.getName() + "_ResourceBundle";
        try {
            return ResourceBundle.getBundle(baseName, locale);
        } catch (final MissingResourceException mre) {
            return new ListResourceBundle() {
                @Override
                protected Object[][] getContents() {
                    return Arrays.stream(enumClass.getEnumConstants())
                            .map(c -> new Object[]{Objects.toString(c.attribute()), c.name()})
                            .toArray(Object[][]::new);
                }
            };
        }
    }

    static <E extends Enum<E> & AttributeEnum<?>> Map<String, String> getAttributeStringMap(
            final Class<E> enumClass, final Locale locale) {
        final ResourceBundle bundle = getResourceBundle(enumClass, locale);
        return Arrays.stream(enumClass.getEnumConstants())
                .collect(Collectors.toMap(
                        c -> Objects.toString(c.attribute()),
                        c -> bundle.getString(c.name()),
                        (v1, v2) -> {
                            throw new RuntimeException("duplicate values: " + v1 + ", " + v2);
                        },
                        LinkedHashMap::new
                ));
    }

    /**
     * Returns the attribute value of this constant.
     *
     * @return the attribute value of this constant.
     * @apiNote Default implementation find the field named as {@value AttributeEnumConstants#FIELD_NAME_ATTRIBUTE}, and
     * returns its value.
     */
    @NotNull
    @SuppressWarnings({"unchecked"})
    default A attribute() {
        try {
            final Field field = getClass().getDeclaredField(AttributeEnumConstants.FIELD_NAME_ATTRIBUTE);
            if (!field.canAccess(this)) {
                field.setAccessible(true);
            }
            final Object value = field.get(this);
            return (A) Objects.requireNonNull(value, "value is null");
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(
                    "failed to get the value of '" + AttributeEnumConstants.FIELD_NAME_ATTRIBUTE + "' field from "
                    + this,
                    roe
            );
        }
    }
}
