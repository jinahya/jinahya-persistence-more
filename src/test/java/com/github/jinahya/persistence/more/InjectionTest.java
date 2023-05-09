package com.github.jinahya.persistence.more;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

//@ExtendWith({WeldJunit5AutoExtension.class})
@EnableAutoWeld
@AddPackages({InjectionTest.class})
@Slf4j
class InjectionTest {

    @Test
    void test() {
//        log.debug("entityManagerFactory: {}", entityManagerFactory);
        final EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityTransaction.commit();
    }

    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Inject
    private EntityManager entityManager;
}
