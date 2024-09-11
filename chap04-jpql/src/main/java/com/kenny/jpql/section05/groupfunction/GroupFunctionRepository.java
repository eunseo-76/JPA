package com.kenny.jpql.section05.groupfunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class GroupFunctionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public long countMenuOfCategory(int categoryCode) {
        String jpql = "SELECT COUNT(m.menuCode) FROM Section05Menu m WHERE m.categoryCode = :categoryCode";
        long countOfMenu = entityManager.createQuery(jpql, Long.class)
                .setParameter("categoryCode", categoryCode)
                .getSingleResult();

        return countOfMenu;
    }

    public Long sumMenuPriceOfCategory(int categoryCode) {
        String jpql = "SELECT SUM(m.menuCode) FROM Section05Menu m WHERE m.categoryCode = :categoryCode";
        /* Count 외의 그룹 함수는 결과 앖이 없을 경우 null이 반환되기 때문에
        * 기본 자료형으로 다룰 경우 문제가 생긴다. Wrapper 클래스 자료형으로 다루어 null 처리가 되도록 한다. (long이 아니라 Long) */
        Long sumMenuPrice = entityManager.createQuery(jpql, Long.class) // 정수값 -> Long
                .setParameter("categoryCode", categoryCode)
                .getSingleResult();

        return sumMenuPrice;
    }
}
