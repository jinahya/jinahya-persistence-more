package com.github.jinahya.persistence.more;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@ApplicationScoped
class EntityManagerProducer {

    @Produces
    public EntityManager produceEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void disposeEntityManager(@Disposes final EntityManager entityManager) {
        entityManager.close();
    }

    @Inject
    private EntityManagerFactory entityManagerFactory;
}
