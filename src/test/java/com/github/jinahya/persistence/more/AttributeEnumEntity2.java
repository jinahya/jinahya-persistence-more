package com.github.jinahya.persistence.more;

import com.github.jinahya.persistence.more.AttributeEnumEntity1.TypeEnum;
import com.github.jinahya.persistence.more.AttributeEnumEntity1.TypeEnum1;
import com.github.jinahya.persistence.more.AttributeEnumEntity1.TypeEnum2;
import jakarta.persistence.Convert;
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

import java.util.Set;

@Entity
@Table(name = "attribute_enum2")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
class AttributeEnumEntity2 {

    public static class TypeEnumAttributeConverter
            extends AttributeEnumConverter.OfString<TypeEnum<?>> {

        public TypeEnumAttributeConverter() {
            super(Set.of(TypeEnum1.class, TypeEnum2.class));
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = TypeEnumAttributeConverter.class)
    private TypeEnum<?> type;
}
