package com.kenny.section01.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EntityManagerGenerator {
    //
    public static EntityManager getInstance() {
        // EntityManagerFactory는 static이므로 하나 뿐(?)
        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();
        return factory.createEntityManager();
    }
}

// EntityManager는 요청이 들어올 때마다 만듦