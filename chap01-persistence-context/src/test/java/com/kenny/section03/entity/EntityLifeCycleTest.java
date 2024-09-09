package com.kenny.section03.entity;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EntityLifeCycleTest {

    private EntityLifeCycle entityLifeCycle;

    @BeforeEach
    void init() {
        this.entityLifeCycle = new EntityLifeCycle();
    }

    @DisplayName("비영속 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void testTransient(int menuCode) {
        // when
        Menu foundMenu = entityLifeCycle.findMenuByMenuCode(menuCode);  // foundMenu : 영속 상태 엔터티. 영속성 컨텍스트에서 찾아오게 한 엔터티

        // newMenu : 비영속 상태 엔터티. 직접 생성한 엔터티. (영속성 컨텍스트에서 찾아온 엔터티가 아님)
        Menu newMenu = new Menu(
                foundMenu.getMenuCode(),
                foundMenu.getMenuName(),
                foundMenu.getMenuPrice(),
                foundMenu.getCategoryCode(),
                foundMenu.getOrderableStatus()
        );

        EntityManager entityManager = entityLifeCycle.getManagerInstance();

        // then
        assertTrue(entityManager.contains(foundMenu));  // foundMenu : 영속성 컨텍스트에서 관리되는 영속 상태의 객체
        assertFalse(entityManager.contains(newMenu));    // newMenu : 영속성 컨텍스트에서 관리되지 않는 비영속 상태의 객체
    }

    @DisplayName("다른 엔터티 매니저가 관리하는 엔터티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void testManagedOtherEntityManager(int menuCode) {
        // when
        Menu menu1 = entityLifeCycle.findMenuByMenuCode(menuCode);  // 첫 번째 엔터티 매니저에 대한 영속성 컨텍스트 생성, DB에서 1번을 가져와 관리
        Menu menu2 = entityLifeCycle.findMenuByMenuCode(menuCode);  // 두 번째 엔터티 매니저에 대한 별도의 영속성 컨텍스트 생성, DB에서 1번을 가져와 관리

        // then
        assertNotEquals(menu1, menu2);
    }

    @DisplayName("같은 엔터티 매니저가 관리하는 엔터티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void testManagedSameEntityManager(int menuCode) {
        // when
        EntityManager entityManager = EntityManagerGenerator.getInstance(); // 하나의 영속성 컨텍스트 생성, DB에서 1번을 가져와 반환. 다시 한 번 1번 조회 시 1차 캐시에서 반환.
        Menu menu1 = entityManager.find(Menu.class, menuCode);
        Menu menu2 = entityManager.find(Menu.class, menuCode);

        // then
        assertEquals(menu1, menu2);
    }
}