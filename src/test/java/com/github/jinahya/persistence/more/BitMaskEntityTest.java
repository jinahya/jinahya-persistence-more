package com.github.jinahya.persistence.more;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.persistence.more.BitMaskTestUtils.OfLong.randomBitMaskOfLong;
import static com.github.jinahya.persistence.more.BitMaskTestUtils.randomBitMask;

class BitMaskEntityTest
        extends PersistenceTest {

    @Test
    void __() {
        final var entity = BitMaskEntity.builder().build();
        entityManager().persist(entity);
        entityManager().flush();
        entity.setBitMask(randomBitMask());
        entity.setBitMaskOfLong(randomBitMaskOfLong());
        entityManager().merge(entity);
        entityManager().flush();
    }
}
