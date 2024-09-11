package com.kenny.jpql.section07.subquery;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> selectWithSubQuery(String categoryName) { // categoryName에 해당하는 categoryCode를 조회하여 subquery로 쓴다.
        String jpql = "SELECT m FROM Section07Menu m WHERE m.categoryCode = (" +
                "SELECT c.categoryCode FROM Section07Category c WHERE c.categoryName = : categoryName)";

        return entityManager.createQuery(jpql, Menu.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
    }
}
