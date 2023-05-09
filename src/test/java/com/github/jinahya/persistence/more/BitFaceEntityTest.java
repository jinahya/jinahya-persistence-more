package com.github.jinahya.persistence.more;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.persistence.more.BitFaceTestUtils.OfLong.randomBitFaceOfLong;
import static com.github.jinahya.persistence.more.BitFaceTestUtils.randomBitFace;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class BitFaceEntityTest
        extends PersistenceTest {

    @Test
    void test() {
        final var entity = BitFaceEntity.builder().build();
        entityManager().persist(entity);
        entityManager().flush();
        assertThat(entity.getId()).isNotNull();
        entity.setBitFace(randomBitFace());
        entity.setBitFaceOfLong(randomBitFaceOfLong());
        entityManager().merge(entity);
        entityManager().flush();
    }
}
