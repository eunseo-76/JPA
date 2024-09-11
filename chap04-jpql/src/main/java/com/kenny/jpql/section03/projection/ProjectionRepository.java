package com.kenny.jpql.section03.projection;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> singleEntityProjection() {
        String jpql = "SELECT m FROM Section03Menu m";  // SELECT 절에 엔터티가 들어간다. 영속성 컨텍스트에서 관리된다.
        return entityManager.createQuery(jpql, Menu.class).getResultList();
    }

    public List<MenuInfo> embeddedTypeProjection() {
        String jpql = "SELECT m.menuInfo FROM EmbeddedMenu m";  // MenuInfo는 엔터티가 아니므로 FROM 절에 사용 불가. 단 프로젝션의 대상(SELECT)로는 사용 가능.
        return entityManager.createQuery(jpql, MenuInfo.class).getResultList(); // MenuInfo는 영속성 컨텍스트에서 관리되는 대상이 아니라 일반 클래스이다.
    }

    public List<Object[]> scalarTypeProjection() {
        String jpql = "SELECT c.categoryCode, c.categoryName FROM Section03Category c";  // 숫자, 문자 타입 <- 단일 값(scalar 타입) / 2개의 타입을 mappin 할 타입이 없음.
        return entityManager.createQuery(jpql).getResultList(); // 2번째 매개변수 생략 시 Object 타입 반환. 단, 2개 이므로 Object 배열 사용
    }

    // 위와 달리 mapping 할 타입을 만듦
    public List<CategoryInfo> newCommandProjection() {
        // categoryInfo의 생성자를 호출
        String jpql = "SELECT new com.kenny.jpql.section03.projection.CategoryInfo(c.categoryCode, c.categoryName) FROM Section03Category c";  // 숫자, 문자 타입 <- 단일 값(scalar 타입) / 2개의 타입을 mappin 할 타입이 없음.
        return entityManager.createQuery(jpql, CategoryInfo.class).getResultList(); //CategoryInfo 타입 변환
    }


}
