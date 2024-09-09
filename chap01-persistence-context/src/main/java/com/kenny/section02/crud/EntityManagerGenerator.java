package com.kenny.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// section01 의 EntityManagerFactory, EntityManagerGenerator를 하나로 합침
public class EntityManagerGenerator {
    private static EntityManagerFactory factory
            = Persistence.createEntityManagerFactory("jpatest");

    private EntityManagerGenerator() {}

    // 요청마다 필요한 EntityManager 객체
    public static EntityManager getInstance() {
        return factory.createEntityManager();
    }

}
