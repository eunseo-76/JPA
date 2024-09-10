package com.kenny.associationmapping.section03.bidirection;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BiDirectionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // 서로의 관점으로 조회하는 2개의 동작

    public Menu findMenu(int menuCode) {
        return entityManager.find(Menu.class, menuCode);
    }

    public Category findCategory(int categoryCode) {
        return entityManager.find(Category.class, categoryCode);
    }
    // @transaction으로 묶어주지 않으면 여기서 한 번 실행되고 끝나기 때문에 세션이 닫혔다는 오류가 발생한다.

}
