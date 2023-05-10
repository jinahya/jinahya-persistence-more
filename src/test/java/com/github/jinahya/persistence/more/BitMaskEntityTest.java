package com.github.jinahya.persistence.more;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.persistence.more.BitMaskTestUtils.OfLong.randomBitMaskOfLong;
import static com.github.jinahya.persistence.more.BitMaskTestUtils.randomBitMask;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class BitMaskEntityTest
        extends PersistenceTest {

    @Test
    void __() {
        final var entity = BitMaskEntity.builder().build();
        {
            entityManager().persist(entity);
            entityManager().flush();
            assertThat(entity).satisfies(e -> {
                assertThat(e.getId()).isNotNull();
                assertThat(e.getBitMask()).isNull();
                assertThat(e.getBitMaskOfLong()).isNull();
                assertThat(e.getBitMaskSet()).isNotNull();
                assertThat(e.getBitMaskOfLongSet()).isNotNull();
            });
        }
        {
            entity.setBitMask(randomBitMask());
            entity.setBitMaskOfLong(randomBitMaskOfLong());
            entity.getBitMaskSet().add(randomBitMask());
            entity.getBitMaskOfLongSet().add(randomBitMaskOfLong());
            entityManager().merge(entity);
            entityManager().flush();
        }
    }
}
