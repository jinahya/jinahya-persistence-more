package com.github.jinahya.persistence.more.converter;

import java.util.UUID;

class UuidBinaryConverterTest extends _AttributeConverterTest<UuidBinaryConverter, UUID, byte[]> {

    UuidBinaryConverterTest() {
        super(UuidBinaryConverter.class, java.util.UUID.class, byte[].class);
    }
}
