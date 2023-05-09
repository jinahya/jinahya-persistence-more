package com.github.jinahya.persistence.more;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@EnableAutoWeld
@AddPackages({EntityManagerFactoryProducer.class})
@Slf4j
abstract class PersistenceTest {

    @BeforeEach
    void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void commitTransaction() {
        entityManager.getTransaction().commit();
    }

//    protected <R> R applyEntityManager(final Function<? super EntityManager, ? extends R> function,
//                                       final boolean inTransaction) {
//        Objects.requireNonNull(function, "function is null");
//        if (inTransaction) {
//            entityManager.getTransaction().begin();
//        }
//        try {
//            return function.apply(entityManager);
//        } catch (final Exception e) {
//        } finally {
//            if (inTransaction) {
//                entityManager.getTransaction().commit();
//            }
//        }
//    }
//
//    protected void acceptEntityManager(final Consumer<? super EntityManager> consumer,
//                                       final boolean inTransaction) {
//        applyEntityManager(
//                em -> {
//                    consumer.accept(em);
//                    return null;
//                },
//                inTransaction
//        );
//    }

    @Inject
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private EntityManager entityManager;
}
