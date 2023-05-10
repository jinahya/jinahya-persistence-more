package com.github.jinahya.persistence.more;

import com.github.jinahya.persistence.more.AttributeEnumEntity1.TypeEnum;
import com.github.jinahya.persistence.more.AttributeEnumEntity1.TypeEnum1;
import com.github.jinahya.persistence.more.AttributeEnumEntity1.TypeEnum2;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AttributeEnumEntity1_Test {

    @Nested
    class TypeEnumTest {

        @EnumSource(TypeEnum1.class)
        @ParameterizedTest
        void valueOfAttribute__TypeEnum1(final TypeEnum1 typeEnum) {
            final var value = TypeEnum.valueOfAttribute(TypeEnum1.class, typeEnum.attribute());
            assertThat(value).isSameAs(typeEnum);
        }

        @EnumSource(TypeEnum2.class)
        @ParameterizedTest
        void valueOfAttribute__TypeEnum1(final TypeEnum2 typeEnum) {
            final var value = TypeEnum.valueOfAttribute(TypeEnum2.class, typeEnum.attribute());
            assertThat(value).isSameAs(typeEnum);
        }

        @Test
        void valueOfAttribute__A() {
            final var attribute = "A";
            assertThat(TypeEnum.valueOfAttribute(TypeEnum1.class, attribute)).isSameAs(TypeEnum1.A);
            assertThatThrownBy(() -> TypeEnum.valueOfAttribute(TypeEnum2.class, attribute))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThat(TypeEnum.valueOfAttribute(attribute, TypeEnum1.class, TypeEnum2.class)).isSameAs(TypeEnum1.A);
            assertThat(TypeEnum.valueOfAttribute(attribute, TypeEnum2.class, TypeEnum1.class)).isSameAs(TypeEnum1.A);
        }

        @Test
        void valueOfAttribute__B() {
            final var attribute = "B";
            assertThat(TypeEnum.valueOfAttribute(TypeEnum1.class, attribute)).isSameAs(TypeEnum1.B);
            assertThat(TypeEnum.valueOfAttribute(TypeEnum2.class, attribute)).isSameAs(TypeEnum2.B);
            assertThat(TypeEnum.valueOfAttribute(attribute, TypeEnum1.class, TypeEnum2.class)).isSameAs(TypeEnum1.B);
            assertThat(TypeEnum.valueOfAttribute(attribute, TypeEnum2.class, TypeEnum1.class)).isSameAs(TypeEnum2.B);
        }

        @Test
        void valueOfAttribute__C() {
            final var attribute = "C";
            assertThatThrownBy(() -> TypeEnum.valueOfAttribute(TypeEnum1.class, attribute))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThat(TypeEnum.valueOfAttribute(TypeEnum2.class, attribute)).isSameAs(TypeEnum2.C);
            assertThat(TypeEnum.valueOfAttribute(attribute, TypeEnum1.class, TypeEnum2.class)).isSameAs(TypeEnum2.C);
            assertThat(TypeEnum.valueOfAttribute(attribute, TypeEnum2.class, TypeEnum1.class)).isSameAs(TypeEnum2.C);
        }
    }
}
