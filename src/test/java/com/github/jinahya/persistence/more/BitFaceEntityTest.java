package com.github.jinahya.persistence.more;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.persistence.more.BitFaceTestUtils.OfLong.randomBitFaceOfLong;
import static com.github.jinahya.persistence.more.BitFaceTestUtils.randomBitFace;
import static com.github.jinahya.persistence.more.BitMaskTestUtils.OfLong.randomBitMaskOfLong;
import static com.github.jinahya.persistence.more.BitMaskTestUtils.randomBitMask;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@Slf4j
class BitFaceEntityTest
        extends PersistenceTest {

    @Test
    void test() {
        final var entity = BitFaceEntity.builder().build();
        entityManager().persist(entity);
        entityManager().flush();
        assertThat(entity.getId()).isNotNull();
        {
            entity.setBitFace(randomBitFace());
            entity.setBitFaceOfLong(randomBitFaceOfLong());
            entityManager().merge(entity);
            entityManager().flush();
        }
        {
            entity.setBitFace(entity.getBitFace().putOn(randomBitMask()));
            entity.setBitFaceOfLong(entity.getBitFaceOfLong().putOn(randomBitMaskOfLong()));
            entityManager().merge(entity);
            entityManager().flush();
        }
        {
            entity.setBitFace(entity.getBitFace().takeOff(randomBitMask()));
            entity.setBitFaceOfLong(entity.getBitFaceOfLong().takeOff(randomBitMaskOfLong()));
            entityManager().merge(entity);
            entityManager().flush();
        }
        {
            {
                final var set = entity.getBitFace().toMaskSet();
                set.add(randomBitMask());
                entity.setBitFace(BitFace.of(set));
            }
            {
                final var set = entity.getBitFaceOfLong().toMaskSet();
                set.add(randomBitMaskOfLong());
                entity.setBitFaceOfLong(BitFace.OfLong.of(set));
            }
            entityManager().merge(entity);
            entityManager().flush();
        }
    }
}
