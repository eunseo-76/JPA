package com.kenny.section03.entity;

import jakarta.persistence.EntityManager;

public class EntityLifeCycle {

    private EntityManager entityManager;

    // 메뉴 코드를 전달 받아 해당하는 메뉴를 찾아와 반환
    // 1차 캐시에 있으면 바로 반환
    // 1차 캐시에 없으면 db에서 select해서 가져와 영속성 컨텍스트에 저장 후 반환
    public Menu findMenuByMenuCode(int menuCode) {
        entityManager = EntityManagerGenerator.getInstance();
        return entityManager.find(Menu.class, menuCode);
    }

    public EntityManager getManagerInstance() {
        return entityManager;
    }
}
