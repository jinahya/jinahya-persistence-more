package com.github.jinahya.persistence.more.converter;

import jakarta.persistence.AttributeConverter;

import java.util.Objects;

abstract class _AttributeConverterTest<T extends AttributeConverter<X, Y>, X, Y> {

    _AttributeConverterTest(final Class<T> converterClass, final Class<X> attributeClass, final Class<Y> dbDataClass) {
        super();
        this.converterClass = Objects.requireNonNull(converterClass, "converterClass is null");
        this.attributeClass = Objects.requireNonNull(attributeClass, "attributeClass is null");
        this.dbDataClass = Objects.requireNonNull(dbDataClass, "dbDataClass is null");
    }

    private final Class<T> converterClass;
    private final Class<X> attributeClass;
    private final Class<Y> dbDataClass;
}
