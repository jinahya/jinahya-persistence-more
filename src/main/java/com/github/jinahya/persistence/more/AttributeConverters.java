package com.github.jinahya.persistence.more;

import jakarta.persistence.AttributeConverter;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Predefined attribute converter classes.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class AttributeConverters {

    /**
     * An attribute converter for converting an {@code int} attribute value into a column value of specified type, and
     * vice versa.
     *
     * @param <Y> type of column value.
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public abstract static class OfInt<Y>
            implements AttributeConverter<Integer, Y> {

        /**
         * Creates a new instance with specified functions.
         *
         * @param converter1 an int function for converting an attribute value of {@code int} into a column value of
         *                   {@link Y}.
         * @param converter2 a function for converting a column value of {@link Y} into an attribute value of
         *                   {@code int}.
         */
        protected OfInt(final IntFunction<? extends Y> converter1, final ToIntFunction<? super Y> converter2) {
            super();
            this.converter1 = Objects.requireNonNull(converter1, "converter1 is null");
            this.converter2 = Objects.requireNonNull(converter2, "converter2 is null");
        }

        /**
         * Converts specified entity attribute to a column value.
         *
         * @param attribute the entity attribute to convert; must be not {@code null}.
         * @return a column value of {@link Y}.
         */
        @Override
        public Y convertToDatabaseColumn(final Integer attribute) {
            return converter1.apply(Objects.requireNonNull(attribute, "attribute is null"));
        }

        /**
         * Converts specified column value to an {@code int} value.
         *
         * @param dbData the column value to convert.
         * @return an attribute value of {@code int}; never {@code null}.
         */
        @Override
        public Integer convertToEntityAttribute(final Y dbData) {
            return converter2.applyAsInt(dbData);
        }

        private final IntFunction<? extends Y> converter1;

        private final ToIntFunction<? super Y> converter2;
    }

    /**
     * An attribute converter for converting an attribute value of {@code long} into a column value of specified type,
     * and vice versa.
     *
     * @param <Y> type of column value.
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public abstract static class OfLong<Y>
            implements AttributeConverter<Long, Y> {

        /**
         * Creates a new instance with specified functions.
         *
         * @param converter1 an int function for converting an attribute value of {@code long} into a column value of
         *                   {@link Y}.
         * @param converter2 a function for converting a column value of {@link Y} into an attribute value of
         *                   {@code long}.
         */
        protected OfLong(final LongFunction<? extends Y> converter1, final ToLongFunction<? super Y> converter2) {
            super();
            this.converter1 = Objects.requireNonNull(converter1, "converter1 is null");
            this.converter2 = Objects.requireNonNull(converter2, "converter2 is null");
        }

        /**
         * Converts specified entity attribute to a column value.
         *
         * @param attribute the entity attribute to convert; must be not {@code null}.
         * @return a column value of {@link Y}.
         */
        @Override
        public Y convertToDatabaseColumn(final Long attribute) {
            return converter1.apply(Objects.requireNonNull(attribute, "attribute is null"));
        }

        /**
         * Converts specified column value to a {@code long} value.
         *
         * @param dbData the column value to convert.
         * @return an attribute value of {@code long}; never {@code null}.
         */
        @Override
        public Long convertToEntityAttribute(final Y dbData) {
            return converter2.applyAsLong(dbData);
        }

        private final LongFunction<? extends Y> converter1;

        private final ToLongFunction<? super Y> converter2;
    }

    /**
     * An abstract attribute converter for constructing with functions.
     *
     * @param <X> entity attribute type parameter
     * @param <Y> column value type parameter
     */
    public abstract static class OfFunctions<X, Y>
            implements AttributeConverter<X, Y> {

//        public static <A, B> Function<? super A, ? extends B> applyingNonNull(
//                final Supplier<? extends A> supplier, final Function<? super A, ? extends B> function) {
//            Objects.requireNonNull(supplier, "supplier is null");
//            Objects.requireNonNull(function, "function is null");
//            return a -> {
//                if (a == null) {
//                    a = Objects.requireNonNull(supplier.get(), "null supplied");
//                }
//                return function.apply(a);
//            };
//        }
//
//        public static <A, B> Function<? super A, ? extends B> nullAsIsOrApplying(
//                final Function<? super A, ? extends B> function) {
//            Objects.requireNonNull(function, "function is null");
//            return a -> {
//                if (a == null) {
//                    return null;
//                }
//                return function.apply(a);
//            };
//        }

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
