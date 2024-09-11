package com.kenny.jpql.section02.parameter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParameterBindingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // 이름을 이용해 바인딩
    public List<Menu> selectMenuByBindingName(String menuName) {
        String jpql = "SELECT m FROM Section02Menu m WHERE m.menuName = :menuName";
        List<Menu> resultMenuList = entityManager.createQuery(jpql, Menu.class) // TypedQuery<Menu> 반환
                .setParameter("menuName", menuName).getResultList();    // 여전히 TypedQuery 타입이다.
        return resultMenuList;
    }

    // 위치를 이용해 바인딩
    public List<Menu> selectMenuByBindingPosition(String menuName) {
        String jpql = "SELECT m FROM Section02Menu m WHERE m.menuName = ?1";
        List<Menu> resultMenuList = entityManager.createQuery(jpql, Menu.class)
                .setParameter(1, menuName).getResultList();    // 1번 위치에 바인딩 됨
        return resultMenuList;
    }
}
