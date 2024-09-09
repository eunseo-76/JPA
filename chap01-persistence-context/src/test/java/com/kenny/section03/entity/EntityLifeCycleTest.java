package com.kenny.section03.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @DisplayName("준형속화 detach 테스트")
    @ParameterizedTest
    @CsvSource({"11, 1000", "12, 1000"})
    void testDetach(int menuCode, int menuPrice) {
        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        // when
        entityTransaction.begin();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);  // foundMenu : 영속 엔터티 (1차 캐시에 있거나, 혹은 DB에서 가져옴)

        // detach : 특정 엔터티만 준영속 상태(영속성 컨텍스트가 관리하던 객체를 관리하지 않는 상태)로 만든다.
        entityManager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);

        // flush : 영속성 컨텍스트의 상태를 DB로 내보낸다. 단, commit 하지 않은 상태이므로 rollback 가능하다.
        // In memory DB ---> DB 로 객체를 내보냄
        entityManager.flush();

        // then
        assertNotEquals(menuPrice, entityManager.find(Menu.class, menuCode).getMenuPrice());    // 두 번째 find는 다시 DB에서 가져옴(첫 번째 find 후 detach 해서 영속성 컨텍스트에서 빠졌으므로). 따라서 서로 다른 객체임.
        entityTransaction.rollback();
    }

    @DisplayName("준영속화 detach 테스트")
    @ParameterizedTest
    @CsvSource({"11, 1000", "12, 1000"})
    void testDetachEntity(int menuCode, int menuPrice) {
        //given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        //when
        entityTransaction.begin();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        // detach : 특정 엔터티만 준영속 상태(영속성 컨텍스트가 관리하던 객체를 관리하지 않는 상태)로 만든다.
        entityManager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);
        // flush : 영속성 컨텍스트의 상태를 DB로 내보낸다. 단, commit 하지 않은 상태이므로 rollback 가능하다.
        entityManager.flush();

        // then
        assertNotEquals(menuPrice, entityManager.find(Menu.class, menuCode).getMenuPrice());
        entityTransaction.rollback();

    }

    @DisplayName("detach 후 merge 한 데이터 update 테스트")
    @ParameterizedTest
    @CsvSource({"11, 하양 민트초코죽", "12, 까만 딸기탕후루"})
    void testMergeUpdate(int menuCode, String menuName) {
        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        entityManager.detach(foundMenu);

        // when
        foundMenu.setMenuName(menuName);    // detach 한 객체의 이름 수정
        Menu refoundMenu = entityManager.find(Menu.class, menuCode);

        entityManager.merge(foundMenu);

        // then
        assertEquals(menuName, refoundMenu.getMenuName());

        // flush 하지 않았으므로 DB에는 반영 X

    }

    @DisplayName("detach 후 merge한 데이터 save 테스트")
    @Test
    void testMergeSave() {
        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Menu foundMenu = entityManager.find(Menu.class, 20);
        entityManager.detach(foundMenu);

        //when
        entityTransaction.begin();
        foundMenu.setMenuName("치약맛 초코 아이스크림");
        foundMenu.setMenuCode(999);

        entityManager.merge(foundMenu);
        entityTransaction.commit();

        //then
        assertEquals("치약맛 초코 아이스크림", entityManager.find(Menu.class, 999).getMenuName());
    }

    @DisplayName("준영속화 clear 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void testClearPersistenceContext(int menuCode) {
        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        // when
        // clear : 영속성 컨텍스트를 초기화 한다 -> 영속성 컨텍스트 내의 모든 엔터티는 준영속화 된다.
        entityManager.clear();

        // then
        Menu expectedMenu = entityManager.find(Menu.class, menuCode);
        assertNotEquals(expectedMenu, foundMenu);
    }

    @DisplayName("준영속화 close 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void testClosePersistenceContext(int menuCode) {
        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        // when
        // close : 영속성 컨텍스트가 종료된다 -> 영속성 컨텍스트 내의 모든 엔터티는 준영속화 된다.
        entityManager.close();

        // then
        // 이미 close 한 entityManager에서는 find 할 수 없다.
        assertThrows(   // 아래 코드를 실행하면 exception이 발생한다.
                IllegalStateException.class,
                () -> entityManager.find(Menu.class, menuCode)
        );
    }

    @DisplayName("영속성 엔터티 삭제 remove 테스트")
    @ParameterizedTest
    @ValueSource(ints = {2})
    void testRemoveEntity(int menuCode) {
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        // when
        entityTransaction.begin();

        // remove: 엔터티를 영속성 컨텍스트 및 데이터 베이스에서 삭제한다
        // 단, 트랜잭션을 제어하지 않으면 데이터 베이스에 영구 반영되지 않는다.
        entityManager.remove(foundMenu);
        entityManager.flush();

        // then
        Menu refoundMenu = entityManager.find(Menu.class, menuCode);
        assertNull(refoundMenu);
        entityTransaction.rollback();   // 최종 반영 하고 싶지 않아서 rollback
    }
}