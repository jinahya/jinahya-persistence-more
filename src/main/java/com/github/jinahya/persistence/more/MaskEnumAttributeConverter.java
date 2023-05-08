package com.github.jinahya.persistence.more;

import jakarta.persistence.AttributeConverter;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class MaskEnumAttributeConverter<E extends MaskEnum>
        implements AttributeConverter<Set<E>, Integer> {

    public abstract static class OfLong<E extends MaskEnum.OfLong>
            implements AttributeConverter<Set<E>, Long> {

        protected OfLong(final Set<Class<? extends E>> enumClasses) {
            super();
            Objects.requireNonNull(enumClasses, "enumClasses is null");
            if (enumClasses.isEmpty()) {
                throw new IllegalArgumentException("enumClasses is empty");
            }
            for (final Class<? extends E> enumClass : enumClasses) {
                if (enumClass == null) {
                    throw new IllegalArgumentException("enumClasses contains null");
                }
            }
            this.enumClasses = Collections.unmodifiableSet(enumClasses);
        }

        @Override
        public Long convertToDatabaseColumn(final Set<E> attribute) {
            if (attribute == null) {
                return null;
            }
            return MaskEnums.OfLong.maskOfAll(attribute);
        }

        @Override
        @SuppressWarnings({
                "java:S1168" // return null; // instead of an empty set
        })
        public Set<E> convertToEntityAttribute(final Long dbData) {
            if (dbData == null) {
                return null;
            }
            return enumClasses.stream()
                    .flatMap(c -> Arrays.stream(c.getEnumConstants()).filter(e -> e.isOn(dbData)))
                    .collect(Collectors.toSet());
        }

        protected final Set<Class<? extends E>> enumClasses;
    }

    protected MaskEnumAttributeConverter(final Set<Class<? extends E>> enumClasses) {
        super();
        Objects.requireNonNull(enumClasses, "enumClasses is null");
        if (enumClasses.isEmpty()) {
            throw new IllegalArgumentException("enumClasses is empty");
        }
        for (final Class<? extends E> enumClass : enumClasses) {
            if (enumClass == null) {
                throw new IllegalArgumentException("enumClasses contains null");
            }
        }
        this.enumClasses = Collections.unmodifiableSet(enumClasses);
    }

    @Override
    public Integer convertToDatabaseColumn(final Set<@NotNull E> attribute) {
        if (attribute == null) {
            return null;
        }
        return MaskEnums.maskOfAll(attribute);
    }

    @Override
    @SuppressWarnings({
            "java:S1168" // return null; // instead of an empty set
    })
    public Set<E> convertToEntityAttribute(final Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return enumClasses.stream()
                .flatMap(c -> Arrays.stream(c.getEnumConstants()).filter(e -> e.isOn(dbData)))
                .collect(Collectors.toSet());
    }

    protected final Set<Class<? extends E>> enumClasses;
}
