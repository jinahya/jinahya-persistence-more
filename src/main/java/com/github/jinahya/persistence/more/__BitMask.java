package com.github.jinahya.persistence.more;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

final class __BitMask {

    static final class OfLong {

        static final long VALUE_OF_NONE = 0L;

        static final long VALUE_OF_ALL = Long.MAX_VALUE;

        static final long MIN_VALUE = VALUE_OF_NONE + 1L;

        static final long MAX_VALUE = (VALUE_OF_ALL >> 1) + 1L; // 0x4000000000000000

        static long requireValidValue(final long value) {
            if (value < MIN_VALUE) {
                throw new IllegalArgumentException("value(" + value + ") < " + MIN_VALUE);
            }
            if (value > MAX_VALUE) {
                throw new IllegalArgumentException("value(" + value + ") > " + MAX_VALUE);
            }
            return value;
        }

        private static final Map<Long, OfLong> CACHE = new ConcurrentHashMap<>(new WeakHashMap<>());

        static OfLong of(final long value) {
            return CACHE.computeIfAbsent(requireValidValue(value), OfLong::new);
        }

        static OfLong maskOfLong(final long value) {
            return of(value);
        }

        static long valueOfAll(final Stream<OfLong> stream) {
            Objects.requireNonNull(stream, "stream is null");
            return stream
                    .mapToLong(m -> m.value)
                    .reduce(__BitFace.OfLong.MIN_VALUE, (v1, v2) -> v1 | v2);
        }

        static long valueOfAll(final Iterable<OfLong> iterable) {
            Objects.requireNonNull(iterable, "iterable is null");
            return valueOfAll(StreamSupport.stream(iterable.spliterator(), false));
        }

        /**
         * Creates a new instance.
         */
        private OfLong(final long value) {
            super();
            this.value = requireValidValue(value);
        }

        @Override
        public String toString() {
            return super.toString() + '{' +
                   "value=" + value +
                   '}';
        }

        /**
         * Puts this mask on to specified face.
         *
         * @param face the face to which this mask is put on.
         * @return new face with this mask on.
         */
        __BitFace.OfLong putOnTo(final __BitFace.OfLong face) {
            return Objects.requireNonNull(face, "face is null").putOn(this);
        }

        /**
         * Takes this mask off from specified face.
         *
         * @param face the face from which this mask is take off.
         * @return new face with this mask off.
         */
        __BitFace.OfLong takeOffFrom(final __BitFace.OfLong face) {
            return Objects.requireNonNull(face, "face is null").takeOff(this);
        }

        final long value;
    }

    static final int VALUE_OF_NONE = 0;

    static final int VALUE_OF_ALL = Integer.MAX_VALUE;

    static final int MIN_VALUE = VALUE_OF_NONE + 1;

    static final int MAX_VALUE = (VALUE_OF_ALL >> 1) + 1;

    static int requireValidValue(final int value) {
        if (value < MIN_VALUE) {
            throw new IllegalArgumentException("value(" + value + ") < " + MIN_VALUE);
        }
        if (value > MAX_VALUE) {
            throw new IllegalArgumentException("value(" + value + ") > " + MAX_VALUE);
        }
        return value;
    }

    private static final Map<Integer, __BitMask> CACHE = new ConcurrentHashMap<>(new WeakHashMap<>());

    static __BitMask maskOf(final int value) {
        return CACHE.computeIfAbsent(requireValidValue(value), __BitMask::new);
    }

    static int valueOfAll(final Stream<__BitMask> stream) {
        Objects.requireNonNull(stream, "stream is null");
        return stream
                .mapToInt(m -> m.value)
                .reduce(__BitFace.MIN_VALUE, (v1, v2) -> v1 | v2);
    }

    static int valueOfAll(final Iterable<__BitMask> iterable) {
        Objects.requireNonNull(iterable, "iterable is null");
        return valueOfAll(StreamSupport.stream(iterable.spliterator(), false));
    }

    /**
     * Creates a new instance.
     */
    private __BitMask(final int value) {
        super();
        this.value = requireValidValue(value);
    }

    @Override
    public String toString() {
        return super.toString() + '{' +
               "value=" + value +
               '}';
    }

    /**
     * Puts this mask on to specified face.
     *
     * @param face the face to which this mask is put on.
     * @return new face with this mask on.
     */
    __BitFace putOnTo(final __BitFace face) {
        return Objects.requireNonNull(face, "face is null").putOn(this);
    }

    /**
     * Takes this mask off from specified face.
     *
     * @param face the face from which this mask is take off.
     * @return new face with this mask off.
     */
    __BitFace takeOffFrom(final __BitFace face) {
        return Objects.requireNonNull(face, "face is null").takeOff(this);
    }

    int getValue() {
        return value;
    }

    private final int value;
}
