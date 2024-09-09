package com.kenny.section02.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EntityManagerCRUDTest {

    private EntityManagerCRUD entityManagerCRUD;

    @BeforeEach
        // 테스트가 일어날 때마다 객체 생성
    void init() {
        this.entityManagerCRUD = new EntityManagerCRUD();
    }

    @DisplayName("메뉴 코드로 메뉴 조회")    // 가져왔는지 확인하는 테스트
    @ParameterizedTest  // 파라미터를 넘기며 테스트를 여러 번 할 때
    @CsvSource({"1,1", "2,2", "3,3"})
        // CSV(Comma Seperated Values)는 콤마를 기준으로 구분
    void testFindMethodByMenuCode(int menuCode, int expected) {

        //when
        Menu foundMenu = entityManagerCRUD.findMenuByMenuCode(menuCode);

        //then
        assertEquals(expected, foundMenu.getMenuCode());
        System.out.println("foundMenu = " + foundMenu);
    }

    private static Stream<Arguments> newMenu() {
        return Stream.of(
                Arguments.of(
                        "신메뉴",
                        35000,
                        4,
                        "Y"
                )
        );
    }

    @DisplayName("새로운 메뉴 추가 테스트")
    @ParameterizedTest
    @MethodSource("newMenu")
    void testRegist(String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        // when
        Menu menu = new Menu(menuName, menuPrice, categoryCode, orderableStatus);
        Long count = entityManagerCRUD.saveAndReturnAllCount(menu);

        // then
        assertEquals(31, count);    //  expected : 테스트 전 DB에 존재하는 행 수 + 1

    }

    @DisplayName("메뉴 이름 수정 테스트")
    @ParameterizedTest
    @CsvSource("112, 마이쭈튀김")
    void testModifyMenuName(int menuCode, String menuName) {
        // when
        Menu modifyMenu = entityManagerCRUD.modifyMenuName(menuCode, menuName);
        // then
        assertEquals(menuName, modifyMenu.getMenuName());

        System.out.println(menuCode + " : " + menuName);
    }

    @DisplayName("메뉴 삭제 테스트")
    @ParameterizedTest
    @ValueSource(ints = {113})
    void testRemoveMenu(int menuCode) {
        // when
        Long count = entityManagerCRUD.removeAndReturnAllCount(menuCode);
        // then
        assertEquals(28, count);
    }
}