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
 * @param <E> enum type parameter
 * @param <A> attribute type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AttributeEnumConverter<E extends AttributeEnum<A>, A>
        implements AttributeConverter<E, A> {

    public abstract static class OfInt<E extends AttributeEnum.OfInt>
            implements AttributeConverter<E, Integer> {

        protected OfInt(final Set<Class<? extends E>> enumClasses) {
            super();
            Objects.requireNonNull(enumClasses, "enumClasses is null");
            if (enumClasses.isEmpty()) {
                throw new IllegalArgumentException("enumClasses is empty");
            }
            for (final Class<? extends E> enumClass : enumClasses) {
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
        public Integer convertToDatabaseColumn(final E attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.attributeAsInt();
        }

        @Override
        public E convertToEntityAttribute(final Integer dbData) {
            if (dbData == null) {
                return null;
            }
            return enumConstants.stream()
                    .filter(ec -> ec.attributeAsInt() == dbData)
                    .findFirst()
                    .orElseThrow(() -> new PersistenceException(
                            "no enum constant matched, in " + enumClasses + ", for " + dbData));
        }

        protected final Set<Class<? extends E>> enumClasses;

        private final Set<E> enumConstants;
    }

    public abstract static class OfLong<E extends AttributeEnum.OfLong>
            implements AttributeConverter<E, Long> {

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
            this.enumClasses = Set.copyOf(enumClasses);
            enumConstants = this.enumClasses.stream()
                    .flatMap(ec -> stream(ec.getEnumConstants())).collect(Collectors.toUnmodifiableSet())
            ;
        }

        @Override
        public Long convertToDatabaseColumn(final E attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.attributeAsLong();
        }

        @Override
        public E convertToEntityAttribute(final Long dbData) {
            if (dbData == null) {
                return null;
            }
            return enumConstants.stream()
                    .filter(ec -> ec.attributeAsLong() == dbData)
                    .findFirst()
                    .orElseThrow(() -> new PersistenceException(
                            "no enum constant matched, in " + enumClasses + ", for " + dbData));
        }

        protected final Set<Class<? extends E>> enumClasses;

        private final Set<E> enumConstants;
    }

    public abstract static class OfCharacter<E extends AttributeEnum.OfCharacter>
            extends AttributeEnumConverter<E, Character> {

        protected OfCharacter(final Set<Class<? extends E>> enumClasses) {
            super(enumClasses);
        }
    }

    public abstract static class OfString<E extends AttributeEnum.OfString>
            extends AttributeEnumConverter<E, String> {

        protected OfString(final Set<Class<? extends E>> enumClasses) {
            super(enumClasses);
        }
    }

    protected AttributeEnumConverter(final Set<Class<? extends E>> enumClasses) {
        super();
        Objects.requireNonNull(enumClasses, "enumClasses is null");
        if (enumClasses.isEmpty()) {
            throw new IllegalArgumentException("enumClasses is empty");
        }
        for (final Class<? extends E> enumClass : enumClasses) {
            if (enumClass == null) {
                throw new IllegalArgumentException("one of enumClasses is null");
            }
        }
        this.enumClasses = Set.copyOf(enumClasses);
        enumConstants = this.enumClasses.stream()
                .flatMap(ec -> stream(ec.getEnumConstants())).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public A convertToDatabaseColumn(final E attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.attribute();
    }

    @Override
    public E convertToEntityAttribute(final A dbData) {
        if (dbData == null) {
            return null;
        }
        return enumConstants.stream()
                .filter(ec -> Objects.equals(ec.attribute(), dbData))
                .findFirst()
                .orElseThrow(() -> new PersistenceException(
                        "no enum constant matched, in " + enumClasses + ", for " + dbData));
    }

    protected final Set<Class<? extends E>> enumClasses;

    private final Set<E> enumConstants;
}
