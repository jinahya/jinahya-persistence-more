package com.github.jinahya.persistence.more;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

final class __BitFace {

    static final class OfLong {

        static final long MIN_VALUE = __BitMask.OfLong.VALUE_OF_NONE;

        static final long MAX_VALUE = __BitMask.OfLong.VALUE_OF_ALL;

        static long requireValidValue(final long value) {
            if (value < MIN_VALUE) {
                throw new IllegalArgumentException("value(" + value + " < " + MIN_VALUE);
            }
            if (false && value > MAX_VALUE) { // TODO: remove!
                throw new IllegalArgumentException("value(" + value + " > " + MAX_VALUE);
            }
            return value;
        }

        private static final Map<Long, OfLong> CACHE = new ConcurrentHashMap<>(new WeakHashMap<>());

        static OfLong of(final long value) {
            return CACHE.computeIfAbsent(requireValidValue(value), OfLong::new);
        }

        private OfLong(final long value) {
            super();
            this.value = value;
        }

        @Override
        public String toString() {
            return super.toString() + '{' +
                   "value=" + value +
                   '}';
        }

        /**
         * Checks whether this face is waring specified mask.
         *
         * @param mask the mask to check.
         * @return {@code true} if this face is wearing {@code mask}; {@code false} otherwise.
         */
        boolean isWearing(final __BitMask.OfLong mask) {
            Objects.requireNonNull(mask, "mask is null");
            return (value & mask.value) == mask.value;
        }

        /**
         * Puts specified mask on to this face.
         *
         * @param mask the mask to put on.
         * @return a new face with {@code mask} on.
         */
        OfLong putOn(final __BitMask.OfLong mask) {
            Objects.requireNonNull(mask, "mask is null");
            return of(value | mask.value);
        }

        /**
         * Takes specified mask off.
         *
         * @param mask the mask to take off.
         * @return a new face with {@code mask} off.
         */
        OfLong takeOff(final __BitMask.OfLong mask) {
            Objects.requireNonNull(mask, "mask is null");
            return of(value & ~mask.value);
        }

        final long value;
    }

    static final int MIN_VALUE = __BitMask.VALUE_OF_NONE;

    static final int MAX_VALUE = __BitMask.VALUE_OF_ALL;

    static int requireValidValue(final int value) {
        if (value < MIN_VALUE) {
            throw new IllegalArgumentException("value(" + value + ") < " + MIN_VALUE);
        }
        if (false && value > MAX_VALUE) { // TODO: remove!
            throw new IllegalArgumentException("value(" + value + ") > " + MAX_VALUE);
        }
        return value;
    }

    private static final Map<Integer, __BitFace> CACHE = new ConcurrentHashMap<>(new WeakHashMap<>());

    /**
     * Returns an instance with specified vlaue.
     *
     * @param value the value.
     * @return an instance.
     */
    static __BitFace of(final int value) {
        return CACHE.computeIfAbsent(requireValidValue(value), __BitFace::new);
    }

    /**
     * Creates a new instance with specified value.
     *
     * @param value the value.
     */
    private __BitFace(final int value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + '{' +
               "value=" + value +
               '}';
    }

    /**
     * Checks whether this face is waring specified mask.
     *
     * @param mask the mask to check.
     * @return {@code true} if this face is wearing {@code mask}; {@code false} otherwise.
     */
    boolean isWearing(final __BitMask mask) {
        Objects.requireNonNull(mask, "mask is null");
        return (value & mask.getValue()) == mask.getValue();
    }

    /**
     * Puts specified mask on.
     *
     * @param mask the mask to put on.
     * @return a new face with {@code mask} on.
     */
    __BitFace putOn(final __BitMask mask) {
        Objects.requireNonNull(mask, "mask is null");
        return of(value | mask.getValue());
    }

    /**
     * Takes specified mask off.
     *
     * @param mask the mask to take off.
     * @return a new face with {@code mask} off.
     */
    __BitFace takeOff(final __BitMask mask) {
        Objects.requireNonNull(mask, "mask is null");
        return of(value & ~mask.getValue());
    }

    int getValue() {
        return value;
    }

    private final int value;
}
