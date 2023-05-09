package com.github.jinahya.persistence.more;

final class MaskEnumConstants {

    static final class OfLong {

        public static final long MIN_MASK = BitMask.OfLong.MIN_VALUE;

        public static final long MAX_MASK = BitMask.OfLong.MAX_VALUE;

        private OfLong() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static final int MIN_MASK = BitMask.MIN_VALUE;

    static final int MAX_MASK = BitMask.MAX_VALUE;

    static final String FIELD_NAME_MASK = "mask";

    private MaskEnumConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
