package com.github.jinahya.persistence.more;

final class MaskEnumConstants {

    static final class OfLong {

        public static final long MIN_MASK = MaskEnumConstants.MIN_MASK;

        public static final long MAX_MASK = 0x4000000000000000L; // 2^63

        public static final long MIN_MASK_UNSIGNED_INT = MIN_MASK;

        public static final long MAX_MASK_UNSIGNED_INT = 0x80000000L; // 2^31

        private OfLong() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static final int MIN_MASK = 0x00000001;

    static final int MAX_MASK = 0x40000000; // 2^30

    static final String FIELD_NAME_MASK = "mask";

    private MaskEnumConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
