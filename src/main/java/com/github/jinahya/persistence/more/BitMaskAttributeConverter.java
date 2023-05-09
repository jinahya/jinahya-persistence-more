package com.github.jinahya.persistence.more;

import jakarta.persistence.Converter;

import java.util.Set;

@Converter
public class BitMaskAttributeConverter
        extends AttributeConverters.OfFunctions<BitMask, Integer> {

    @Converter
    public static class OfSet
            extends AttributeConverters.OfFunctions<Set<BitMask>, Integer> {

        @Converter
        public static class OfLong
                extends AttributeConverters.OfFunctions<Set<BitMask.OfLong>, Long> {

            public OfLong() {
                super(nullAsIsOrApplying(s -> BitFace.OfLong.of(s).getValue()),
                      nullAsIsOrApplying(v -> BitFace.OfLong.of(v).toMaskSet()));
            }
        }

        public OfSet() {
            super(nullAsIsOrApplying(s -> BitFace.of(s).getValue()),
                  nullAsIsOrApplying(v -> BitFace.of(v).toMaskSet()));
        }
    }

    @Converter
    public static class OfLong
            extends AttributeConverters.OfFunctions<BitMask.OfLong, Long> {

        public OfLong() {
            super(nullAsIsOrApplying(BitMask.OfLong::getValue), nullAsIsOrApplying(BitMask.OfLong::of));
        }
    }

    public BitMaskAttributeConverter() {
        super(nullAsIsOrApplying(BitMask::getValue), nullAsIsOrApplying(BitMask::of));
    }
}
