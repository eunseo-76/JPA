package com.kenny.section01.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityManagerGeneratorTest {

    @Test
    @DisplayName("엔터티 매니저 팩토리 생성 확인")
    void testGenerateEntityManagerFactory() {
        //given
        //when
        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();
        // 반환받은 객체가 null이 아닌지 확인하는 테스트(빨간 로그 말고 왼쪽 v 확인하기)

        //then
        assertNotNull(factory);
    }

    // 프로그램이 시작될 때 만들어진 EntityManagerFactory 인스턴스를 동일하게 사용하는지 확인
    @Test
    @DisplayName("엔터티 매니저 팩토리 싱글톤 인스턴스 확인")
    void testIsEntityManagerFactorySingletonInstance() {
        //given
        //when
        EntityManagerFactory factory1 = EntityManagerFactoryGenerator.getInstance();
        EntityManagerFactory factory2 = EntityManagerFactoryGenerator.getInstance();

        assertEquals(factory1,factory2);
        assertEquals(factory1.hashCode(), factory2.hashCode());
    }

    @Test
    @DisplayName("엔터티 매니저 생성 확인")
    void testGenerateEntityManager() {
        //given
        //when
        EntityManager entityManager = EntityManagerGenerator.getInstance();

        //then
        assertNotNull(entityManager);
    }

    @Test
    @DisplayName("엔터티 매니저 스코프 확인")
    void testEntityManagerLifeCycle() {
        //given
        //when
        EntityManager entityManager1 = EntityManagerGenerator.getInstance();
        EntityManager entityManager2 = EntityManagerGenerator.getInstance();

        //then
        assertNotEquals(entityManager1, entityManager2);
        assertNotEquals(entityManager1.hashCode(), entityManager2.hashCode());
    }

}
