package com.github.jinahya.persistence.more;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@Entity
@Table(name = "attribute_enum1")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
class AttributeEnumEntity1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    public interface TypeEnum<E extends Enum<E> & TypeEnum<E>>
            extends AttributeEnum.OfString {

        static <E extends Enum<E> & TypeEnum<E>> E valueOfAttribute(final Class<E> enumClass, final String attribute) {
            return AttributeEnum.valueOfAttribute(enumClass, attribute);
        }

        static TypeEnum<?> valueOfAttribute(final String attribute, final Class<? extends TypeEnum<?>> enumClass,
                                            final Class<? extends TypeEnum<?>> otherEnumClasses) {
            return AttributeEnum.valueOfAttribute(attribute, enumClass, otherEnumClasses);
        }
    }

    public enum TypeEnum1
            implements TypeEnum<TypeEnum1> {

        A,
        B("B"),
        ;

        TypeEnum1(final String attribute) {
            this.attribute = attribute;
        }

        TypeEnum1() {
            this(null);
        }

        private final String attribute;
    }

    public enum TypeEnum2
            implements TypeEnum<TypeEnum2> {

        B,

        C("C");

        TypeEnum2(final String attribute) {
            this.attribute = attribute;
        }

        TypeEnum2() {
            this(null);
        }

        private final String attribute;
    }

    public <E extends Enum<E> & TypeEnum<E>> E getTypeAsEnum(final Class<E> enumClass) {
        return Optional.ofNullable(getType())
                .map(v -> TypeEnum.valueOfAttribute(enumClass, v))
                .orElse(null);
    }

    public TypeEnum<?> getTypeAsEnum(final Class<? extends TypeEnum<?>> enumClass,
                                     final Class<? extends TypeEnum<?>> otherEnumClasses) {
        return Optional.ofNullable(getType())
                .map(v -> TypeEnum.valueOfAttribute(v, enumClass, otherEnumClasses))
                .orElse(null);
    }

    public void setTypeAsEnum(final TypeEnum<?> typeAsEnum) {
        setType(
                Optional.ofNullable(typeAsEnum)
                        .map(AttributeEnum::attribute)
                        .orElse(null)
        );
    }
}
