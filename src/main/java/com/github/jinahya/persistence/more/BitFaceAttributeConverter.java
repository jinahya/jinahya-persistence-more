package com.github.jinahya.persistence.more;

import jakarta.persistence.Converter;

@Converter
public class BitFaceAttributeConverter
        extends AttributeConverters.OfFunctions<BitFace, Integer> {

    @Converter
    public static class OfLong
            extends AttributeConverters.OfFunctions<BitFace.OfLong, Long> {

        public OfLong() {
            super(nullAsIsOrApplying(BitFace.OfLong::getValue), nullAsIsOrApplying(BitFace.OfLong::of));
        }
    }

    public BitFaceAttributeConverter() {
        super(nullAsIsOrApplying(BitFace::getValue), nullAsIsOrApplying(BitFace::of));
    }
}
