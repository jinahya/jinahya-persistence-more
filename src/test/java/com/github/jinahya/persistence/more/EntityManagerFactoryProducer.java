package com.github.jinahya.persistence.more;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;

@ApplicationScoped
class EntityManagerFactoryProducer {

    @Produces
    @ApplicationScoped
    public EntityManagerFactory produceEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("testPU", new HashMap<>());
    }

    public void disposeEntityManagerFactory(@Disposes final EntityManagerFactory entityManagerFactory) {
        entityManagerFactory.close();
    }
}
