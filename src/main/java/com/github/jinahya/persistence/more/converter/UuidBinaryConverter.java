package com.github.jinahya.persistence.more.converter;

import jakarta.persistence.AttributeConverter;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UuidBinaryConverter
        implements AttributeConverter<UUID, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(final UUID attribute) {
        if (attribute == null) {
            return null;
        }
        return ByteBuffer.allocate(16)
                .putLong(attribute.getMostSignificantBits())
                .putLong(attribute.getLeastSignificantBits())
                .array();
    }

    @Override
    public UUID convertToEntityAttribute(final byte[] dbData) {
        if (dbData == null) {
            return null;
        }
        final ByteBuffer buffer = ByteBuffer.wrap(dbData);
        return new UUID(buffer.getLong(), buffer.getLong());
    }
}
