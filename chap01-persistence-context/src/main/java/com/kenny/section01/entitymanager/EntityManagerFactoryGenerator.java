package com.kenny.section01.entitymanager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryGenerator {

    // EntityManagerFactory 객체 생성
    // 프로그램이 시작될 때 한 번 생성하고, 프로그램이 종료될 때 소멸
    private static EntityManagerFactory factory
            = Persistence.createEntityManagerFactory("jpatest");

    // 생성자를 private로 만든다 = new EntityManagerFactoryGenerator()를 하지 않겠다.
    // EntityManagerFactory를 다시 생성하지 않게 하기 위함
    private EntityManagerFactoryGenerator() {}

    // 설정 정보를 기반으로 EntityManagerFactory가 필요할 때마다 getInstance로 가져간다.
    public static EntityManagerFactory getInstance() {
        return factory;
    }
}

// EntityManagerFactory는 어플리케이션 당 하나만 생성