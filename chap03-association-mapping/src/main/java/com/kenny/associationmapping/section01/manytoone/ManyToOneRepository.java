package com.kenny.associationmapping.section01.manytoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToOneRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Menu find(int menuCode) {
        return entityManager.find(Menu.class, menuCode);
    }

    // find 가 아닌 JPQL 사용
    public String findCategoryName(int menuCode) {
        // JPQL: 컬럼, 테이블이 아닌 필드, 엔터티를 대상으로 한다.
        String jpql = "SELECT c.categoryName FROM section01Menu m JOIN m.category c WHERE m.menuCode = :menuCode";

        return entityManager.createQuery(jpql, String.class).setParameter("menuCode", menuCode).getSingleResult();
    }

    public void regist(Menu menu) {
        entityManager.persist(menu);
    }
}
