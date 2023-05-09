package com.github.jinahya.persistence.more;

import jakarta.persistence.AttributeConverter;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Predefined attribute converter classes.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class AttributeConverters {

    /**
     * An attribute converter for converting an attribute value of {@link X} into a column value of {@code int}, and
     * vice versa.
     *
     * @param <X> type of entity attribute.
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public abstract static class OfInt<X>
            implements AttributeConverter<X, Integer> {

        /**
         * Creates a new instance with specified functions.
         *
         * @param converter1 a function for converting a column value of {@code int} into an attribute value of
         *                   {@link X}.
         * @param converter2 a function for converting an attribute value of {@link X} into a column value of
         *                   {@code int}.
         */
        protected OfInt(final ToIntFunction<? super X> converter1, final IntFunction<? extends X> converter2) {
            super();
            this.converter1 = Objects.requireNonNull(converter1, "converter1 is null");
            this.converter2 = Objects.requireNonNull(converter2, "converter2 is null");
        }

        /**
         * Converts specified entity attribute to a column value.
         *
         * @param attribute the entity attribute to convert; must be not {@code null}.
         * @return a column value of {@link X}.
         */
        @Override
        public Integer convertToDatabaseColumn(final X attribute) {
            return converter1.applyAsInt(Objects.requireNonNull(attribute, "attribute is null"));
        }

        /**
         * Converts specified column value into to an attribute value of {@link X}.
         *
         * @param dbData the column value to convert; must be not {@code}.
         * @return an attribute value of {@link X}; never {@code null}.
         */
        @Override
        public X convertToEntityAttribute(final Integer dbData) {
            return Objects.requireNonNull(converter2.apply(Objects.requireNonNull(dbData, "dbData is null")),
                                          "applied as null");
        }

        private final ToIntFunction<? super X> converter1;

        private final IntFunction<? extends X> converter2;
    }

    /**
     * An attribute converter for converting an attribute value of {@link X} into a column value of {@code long}, and
     * vice versa.
     *
     * @param <X> type of attribute value.
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public abstract static class OfLong<X>
            implements AttributeConverter<X, Long> {

        /**
         * Creates a new instance with specified functions.
         *
         * @param converter1 a function for converting a column value of {@link X} into an attribute value of
         *                   {@code long}.
         * @param converter2 a function for converting an attribute value of {@code long} into a column value of
         *                   {@link X}.
         */
        protected OfLong(final ToLongFunction<? super X> converter1, final LongFunction<? extends X> converter2) {
            super();
            this.converter1 = Objects.requireNonNull(converter1, "converter1 is null");
            this.converter2 = Objects.requireNonNull(converter2, "converter2 is null");
        }

        /**
         * Converts specified entity attribute of {@link X} into a column value of {@code long}.
         *
         * @param attribute the entity attribute to convert; must be not {@code null}.
         * @return a column value of {@link long}.
         */
        @Override
        public Long convertToDatabaseColumn(final X attribute) {
            return converter1.applyAsLong(Objects.requireNonNull(attribute, "attribute is null"));
        }

        /**
         * Converts specified column value of {@code long} into an attribute value of {@link X}.
         *
         * @param dbData the column value to convert; must be not {@code null}.
         * @return an attribute value of {@link X}; never {@code null}.
         */
        @Override
        public X convertToEntityAttribute(final Long dbData) {
            return Objects.requireNonNull(converter2.apply(Objects.requireNonNull(dbData, "dbData is null")),
                                          "applied into null");
        }

        private final ToLongFunction<? super X> converter1;

        private final LongFunction<? extends X> converter2;
    }

    /**
     * An abstract attribute converter for constructing with functions.
     *
     * @param <X> entity attribute type parameter
     * @param <Y> column value type parameter
     */
    public abstract static class OfFunctions<X, Y>
            implements AttributeConverter<X, Y> {

        public static <A, B> Function<? super A, ? extends B> applyingNonNull(
                final Supplier<? extends A> supplier, final Function<? super A, ? extends B> function) {
            Objects.requireNonNull(supplier, "supplier is null");
            Objects.requireNonNull(function, "function is null");
            return a -> {
                if (a == null) {
                    a = Objects.requireNonNull(supplier.get(), "null supplied");
                }
                return function.apply(a);
            };
        }

        public static <A, B> Function<? super A, ? extends B> nullAsIsOrApplying(
                final Function<? super A, ? extends B> function) {
            Objects.requireNonNull(function, "function is null");
            return a -> {
                if (a == null) {
                    return null;
                }
                return function.apply(a);
            };
        }

        /**
         * Creates a new instance with specified converters.
         *
         * @param converter1 a converter for converting an attribute value of {@link X} into a column value of
         *                   {@link Y}.
         * @param converter2 a converter for converting a column value of {@link Y} into an attribute value of
         *                   {@link X}.
         */
        protected OfFunctions(final Function<? super X, ? extends Y> converter1,
                              final Function<? super Y, ? extends X> converter2) {
            super();
            this.converter1 = Objects.requireNonNull(converter1, "converter1 is null");
            this.converter2 = Objects.requireNonNull(converter2, "converter2 is null");
        }

        @Override
        public Y convertToDatabaseColumn(final X attribute) {
            return converter1.apply(attribute);
        }

        @Override
        public X convertToEntityAttribute(final Y dbData) {
            return converter2.apply(dbData);
        }

        private final Function<? super X, ? extends Y> converter1;

        private final Function<? super Y, ? extends X> converter2;
    }

    private AttributeConverters() {
        throw new AssertionError("instantiation is not allowed");
    }
}
