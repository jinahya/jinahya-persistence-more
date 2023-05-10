package com.github.jinahya.persistence.more;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.PersistenceException;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/**
 * An interface for converting {@link AttributeEnum} type to database type, and vice versa.
 *
 * @param <X> enum type parameter
 * @param <Y> attribute type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AttributeEnumConverter<X extends AttributeEnum<Y>, Y>
        implements AttributeConverter<X, Y> {

    public abstract static class OfInt<X extends AttributeEnum.OfInt>
            implements AttributeConverter<X, Integer> {

        protected OfInt(final Set<Class<? extends X>> enumClasses) {
            super();
            Objects.requireNonNull(enumClasses, "enumClasses is null");
            if (enumClasses.isEmpty()) {
                throw new IllegalArgumentException("enumClasses is empty");
            }
            for (final Class<? extends X> enumClass : enumClasses) {
                if (enumClass == null) {
                    throw new IllegalArgumentException("one of enumClasses is null");
                }
            }
            this.enumClasses = Set.copyOf(enumClasses);
            enumConstants = this.enumClasses.stream()
                    .flatMap(ec -> stream(ec.getEnumConstants())).collect(Collectors.toUnmodifiableSet())
            ;
        }

        @Override
        public Integer convertToDatabaseColumn(final X attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.attributeAsInt();
        }

        @Override
        public X convertToEntityAttribute(final Integer dbData) {
            if (dbData == null) {
                return null;
            }
            return enumConstants.stream()
                    .filter(ec -> ec.attributeAsInt() == dbData)
                    .findFirst()
                    .orElseThrow(() -> new PersistenceException(
                            "no enum constant matched, in " + enumClasses + ", for " + dbData));
        }

        protected final Set<Class<? extends X>> enumClasses;

        private final Set<X> enumConstants;
    }

    public abstract static class OfLong<X extends AttributeEnum.OfLong>
            implements AttributeConverter<X, Long> {

        protected OfLong(final Set<Class<? extends X>> enumClasses) {
            super();
            Objects.requireNonNull(enumClasses, "enumClasses is null");
            if (enumClasses.isEmpty()) {
                throw new IllegalArgumentException("enumClasses is empty");
            }
            for (final Class<? extends X> enumClass : enumClasses) {
                if (enumClass == null) {
                    throw new IllegalArgumentException("enumClasses contains null");
                }
            }
            this.enumClasses = Set.copyOf(enumClasses);
            enumConstants = this.enumClasses.stream()
                    .flatMap(ec -> stream(ec.getEnumConstants())).collect(Collectors.toUnmodifiableSet())
            ;
        }

        @Override
        public Long convertToDatabaseColumn(final X attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.attributeAsLong();
        }

        @Override
        public X convertToEntityAttribute(final Long dbData) {
            if (dbData == null) {
                return null;
            }
            return enumConstants.stream()
                    .filter(ec -> ec.attributeAsLong() == dbData)
                    .findFirst()
                    .orElseThrow(() -> new PersistenceException(
                            "no enum constant matched, in " + enumClasses + ", for " + dbData));
        }

        protected final Set<Class<? extends X>> enumClasses;

        private final Set<X> enumConstants;
    }

    public abstract static class OfCharacter<X extends AttributeEnum.OfCharacter>
            extends AttributeEnumConverter<X, Character> {

        protected OfCharacter(final Set<Class<? extends X>> enumClasses) {
            super(enumClasses);
        }
    }

    public abstract static class OfString<X extends AttributeEnum.OfString>
            extends AttributeEnumConverter<X, String> {

        protected OfString(final Set<Class<? extends X>> enumClasses) {
            super(enumClasses);
        }
    }

    protected AttributeEnumConverter(final Set<Class<? extends X>> enumClasses) {
        super();
        Objects.requireNonNull(enumClasses, "enumClasses is null");
        if (enumClasses.isEmpty()) {
            throw new IllegalArgumentException("enumClasses is empty");
        }
        for (final Class<? extends X> enumClass : enumClasses) {
            if (enumClass == null) {
                throw new IllegalArgumentException("one of enumClasses is null");
            }
        }
        this.enumClasses = Set.copyOf(enumClasses);
        enumConstants = this.enumClasses.stream()
                .flatMap(ec -> stream(ec.getEnumConstants())).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Y convertToDatabaseColumn(final X attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.attribute();
    }

    @Override
    public X convertToEntityAttribute(final Y dbData) {
        if (dbData == null) {
            return null;
        }
        return enumConstants.stream()
                .filter(ec -> Objects.equals(ec.attribute(), dbData))
                .findFirst()
                .orElseThrow(() -> new PersistenceException(
                        "no enum constant matched, in " + enumClasses + ", for " + dbData));
    }

    protected final Set<Class<? extends X>> enumClasses;

    private final Set<X> enumConstants;
}
