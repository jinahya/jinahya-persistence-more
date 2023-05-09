package com.github.jinahya.persistence.more;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;

public final class MaskEnums {

    public static final class OfLong {

        public static long requireValidMask(final long mask) {
            return BitMask.OfLong.requireValidValue(mask);
        }

        /**
         * Finds the enum constant of specified enum class whose {@code mask} equals to specified value.
         *
         * @param enumClass the enum class; not {@code null}.
         * @param mask      the mask value to match.
         * @param <E>       enum type parameter
         * @return the enum constant for {@code mask}.
         */
        public static <E extends Enum<E> & MaskEnum.OfLong> E valueOfMask(final Class<E> enumClass, final long mask) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            requireValidMask(mask);
            return Optional.ofNullable(MaskEnumHelper.OfLong.valueOfMask(enumClass, mask))
                    .orElseThrow(() -> new IllegalArgumentException(
                            "no enum constant, in " + enumClass + ", for mask(" + mask + ')'));
        }

        @SafeVarargs
        public static <E extends MaskEnum.OfLong> E valueOfMask(final long mask, final Class<? extends E> enumClass,
                                                                final Class<? extends E>... otherEnumClasses) {
            requireValidMask(mask);
            Objects.requireNonNull(enumClass, "enumClass is null");
            Objects.requireNonNull(otherEnumClasses, "otherEnumClasses is null");
            return Stream.concat(Stream.of(enumClass), Stream.of(otherEnumClasses))
                    .map(ec -> MaskEnumHelper.OfLong.valueOfMask(ec, mask))
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "no enum constant, in either " + enumClass + " + nor in any of "
                            + Arrays.toString(otherEnumClasses) + ", for mask(" + mask + ')'));
        }

        public static long maskOfAll(final Iterable<? extends MaskEnum.OfLong> iterable) {
            Objects.requireNonNull(iterable, "iterable is null");
            return stream(spliteratorUnknownSize(iterable.iterator(), 0), false)
                    .mapToLong(MaskEnum.OfLong::mask)
                    .reduce(0L, (m1, m2) -> m1 | m2);
        }

        public static <E extends Enum<E> & MaskEnum.OfLong> long maskOfAll(final Class<? extends E> enumClass) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            return MaskEnumHelper.OfLong.maskOfAll(enumClass);
        }

        @SafeVarargs
        public static <E extends MaskEnum.OfLong> long maskOfAll(final Class<? extends E> enumClass,
                                                                 final Class<? extends E>... otherEnumClasses) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            Objects.requireNonNull(otherEnumClasses, "otherEnumClasses is null");
            return Stream.concat(Stream.of(enumClass), Stream.of(otherEnumClasses))
                    .mapToLong(MaskEnumHelper.OfLong::maskOfAll)
                    .reduce(0, (m1, m2) -> m1 | m2)
                    ;
        }

        public static <E extends MaskEnum.OfLong> Set<E> valuesOf(
                final long face, final Stream<Class<? extends E>> stream) {
            BitFace.OfLong.requireValidValue(face);
            Objects.requireNonNull(stream, "stream is null");
            return stream.flatMap(ec -> Arrays.stream(ec.getEnumConstants()).filter(m -> m.isOn(face)))
                    .collect(Collectors.toSet());
        }

        public static <E extends MaskEnum.OfLong> Set<E> valuesOf(
                final long face, final Iterable<Class<? extends E>> iterable) {
            Objects.requireNonNull(iterable, "iterable is null");
            return valuesOf(face, StreamSupport.stream(iterable.spliterator(), false));
        }

        public static boolean isWearingAllOf(final long face, final MaskEnum.OfLong mask,
                                             final MaskEnum.OfLong... otherMasks) {
            Objects.requireNonNull(mask, "mask is null");
            Objects.requireNonNull(otherMasks, "otherMasks is null");
            if (!mask.isOn(face)) {
                return false;
            }
            for (final MaskEnum.OfLong otherMask : otherMasks) {
                if (!Objects.requireNonNull(otherMask, "one of otherMasks is null").isOn(face)) {
                    return false;
                }
            }
            return true;
        }

        public static boolean isWearingAnyOf(final long face, final MaskEnum.OfLong mask,
                                             final MaskEnum.OfLong... otherMasks) {
            Objects.requireNonNull(mask, "mask is null");
            Objects.requireNonNull(otherMasks, "otherMasks is null");
            if (mask.isOn(face)) {
                return true;
            }
            for (final MaskEnum.OfLong otherMask : otherMasks) {
                if (Objects.requireNonNull(otherMask, "one of otherMasks is null").isOn(face)) {
                    return true;
                }
            }
            return false;
        }

        private OfLong() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    public static int requireValidMask(final int mask) {
        return BitMask.requireValidValue(mask);
    }

    /**
     * Finds the enum constant of specified enum class whose {@link MaskEnum#mask() mask} matches to specified value.
     *
     * @param enumClass the enum class; not {@code null}.
     * @param mask      the mask value to match; not {@code null}.
     * @param <E>       enum type parameter
     * @return the enum constant for {@code mask}.
     */
    public static <E extends Enum<E> & MaskEnum> E valueOfMask(final Class<E> enumClass, final int mask) {
        Objects.requireNonNull(enumClass, "enumClass is null");
        return Optional.ofNullable(MaskEnumHelper.valueOfMask(enumClass, mask))
                .orElseThrow(() -> new IllegalArgumentException(
                        "no enum constant, in " + enumClass + ", for " + mask));
    }

    @SafeVarargs
    public static <E extends MaskEnum> E valueOfMask(final int mask, Class<? extends E> enumClass,
                                                     final Class<? extends E>... otherEnumClasses) {
        requireValidMask(mask);
        Objects.requireNonNull(enumClass, "enumClass is null");
        Objects.requireNonNull(otherEnumClasses, "otherEnumClasses is null");
        return Stream.concat(Stream.of(enumClass), Stream.of(otherEnumClasses))
                .map(ec -> MaskEnumHelper.valueOfMask(ec, mask))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "no enum constant, in either " + enumClass + " + nor in any of "
                        + Arrays.toString(otherEnumClasses) + ", for " + mask));
    }

    public static int maskOfAll(final Iterable<? extends MaskEnum> iterable) {
        Objects.requireNonNull(iterable, "iterable is null");
        return stream(spliteratorUnknownSize(iterable.iterator(), 0), false)
                .mapToInt(MaskEnum::mask)
                .reduce(0, (m1, m2) -> m1 | m2);
    }

    public static <E extends Enum<E> & MaskEnum> int maskOfAll(final Class<? extends E> enumClass) {
        Objects.requireNonNull(enumClass, "enumClass is null");
        return MaskEnumHelper.maskOfAll(enumClass);
    }

    @SafeVarargs
    public static <E extends MaskEnum> int maskOfAll(final Class<? extends E> enumClass,
                                                     final Class<? extends E>... otherEnumClasses) {
        Objects.requireNonNull(enumClass, "enumClass is null");
        Objects.requireNonNull(otherEnumClasses, "otherEnumClasses is null");
        return Stream.concat(Stream.of(enumClass), Stream.of(otherEnumClasses))
                .mapToInt(MaskEnumHelper::maskOfAll)
                .reduce(0, (m1, m2) -> m1 | m2)
                ;
    }

    public static <E extends MaskEnum> Set<E> valuesOf(final int face, final Stream<Class<? extends E>> stream) {
        BitFace.requireValidValue(face);
        Objects.requireNonNull(stream, "stream is null");
        return stream.flatMap(ec -> Arrays.stream(ec.getEnumConstants()).filter(m -> m.isOn(face)))
                .collect(Collectors.toSet());
    }

    public static <E extends MaskEnum> Set<E> valuesOf(final int face, final Iterable<Class<? extends E>> iterable) {
        Objects.requireNonNull(iterable, "iterable is null");
        return valuesOf(face, StreamSupport.stream(iterable.spliterator(), false));
    }

    public static boolean isWearingAllOf(final int face, final MaskEnum mask, final MaskEnum... otherMasks) {
        Objects.requireNonNull(mask, "mask is null");
        Objects.requireNonNull(otherMasks, "otherMasks is null");
        if (!mask.isOn(face)) {
            return false;
        }
        for (final MaskEnum otherMask : otherMasks) {
            if (!Objects.requireNonNull(otherMask, "one of otherMasks is null").isOn(face)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWearingAnyOf(final int face, final MaskEnum mask, final MaskEnum... otherMasks) {
        Objects.requireNonNull(mask, "mask is null");
        Objects.requireNonNull(otherMasks, "otherMasks is null");
        if (mask.isOn(face)) {
            return true;
        }
        for (final MaskEnum otherMask : otherMasks) {
            if (Objects.requireNonNull(otherMask, "one of otherMasks is null").isOn(face)) {
                return true;
            }
        }
        return false;
    }

    private MaskEnums() {
        throw new AssertionError("instantiation is not allowed");
    }
}
