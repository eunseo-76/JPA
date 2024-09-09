package com.kenny.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EntityManagerCRUD {

    private EntityManager entityManager;

    /* 1. 특정 메뉴 코드로 메뉴를 조회하는 기능 */
    public Menu findMenuByMenuCode(int menuCode) {
        entityManager = EntityManagerGenerator.getInstance();
        return entityManager.find(Menu.class, menuCode);
        // find(반환 받는 타입, pk 값)
    }

    /* 2. 새로운 메뉴 저장하는 기능 */
    public Long saveAndReturnAllCount(Menu newMenu) {
        entityManager = EntityManagerGenerator.getInstance();

        // 트랜잭션
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(newMenu); // 엔터티 저장: persist.

        entityTransaction.commit();

        return getCount(entityManager);
    }

    // 위 기능과 항상 함께 쓴다고 가정하고 entitymanager 공유
    private Long getCount(EntityManager entityManager) {
        return entityManager    // JPQL 문법 -> 별도 챕터에서 다룸
                .createQuery("SELECT COUNT(*) FROM Section02Menu", Long.class)
                .getSingleResult();
    }

    /* 3. 메뉴 이름 수정 기능 */
    public Menu modifyMenuName(int menuCode, String menuName) {
        entityManager = EntityManagerGenerator.getInstance();

        Menu foundMenu = entityManager.find(Menu.class, menuCode);  // 수정하기 위해 먼저 DB에서 객체 찾아옴

        // 트랜잭션
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        foundMenu.setMenuName(menuName);    // 찾아온 객체를 수정

        entityTransaction.commit();

        return foundMenu;
    }

    /* 4. 특정 메뉴 코드로 메뉴 삭제하는 기능 */
    public Long removeAndReturnAllCount(int menuCode) {
        entityManager = EntityManagerGenerator.getInstance();

        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        // 트랜잭션
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.remove(foundMenu);

        entityTransaction.commit();

        return getCount(entityManager);
    }

}

// JPQL : '엔터티'를 대상으로 하는 문법. SQL과 다르다. FROM 뒤에 테이블이 아니라 엔터티가 온다.

// 엔터티 매니저 생성 -> 영속성 컨텍스트(In-Memory DB) 생성됨
// persist 요청 시, 해당 객체가 영속성 컨텍스트에 들어감. 추후에 DB로 내보냄(flush).
// 수정하려면 DB에서 해당하는 것을 찾고 그것을 가져와 객체를 수정하는 작업 필요.