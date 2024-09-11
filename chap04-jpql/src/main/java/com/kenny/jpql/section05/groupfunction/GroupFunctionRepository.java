package com.kenny.jpql.section05.groupfunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Object[]> selectByGroupByHaving(long minPrice) {
        String jpql = "SELECT m.categoryCode, SUM(m.menuPrice) FROM Section05Menu m " + // categoryCode와 SUM을 별도의 타입으로 선언할 수도 있지만 여기서는 Object로 다룬다
                "GROUP BY m.categoryCode HAVING SUM(m.menuPrice) > :minPrice";  // 메뉴 엔터티를 카테고리 코드 기준으로 그루핑한 후, 메뉴 가격의 합이 얼마 이상인 경우만 조회

        return entityManager.createQuery(jpql).setParameter("minPrice", minPrice).getResultList();
    }
}
