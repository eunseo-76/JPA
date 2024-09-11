package com.kenny.jpql.section02.parameter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParameterBindingRepositoryTest {

    @Autowired
    ParameterBindingRepository parameterBindingRepository;

    @DisplayName("이름 기준 파라미터 바인딩 테스트")
    @Test
    void testParameterBindingByName() {
        String menuName = "한우딸기국밥";
        List<Menu> menus = parameterBindingRepository.selectMenuByBindingName(menuName);    // 여러 한우딸기국밥이 있을 수 있기 때문에 List로 반환 (실습 상에서는 하나 뿐이긴 함)
        assertEquals(menuName, menus.get(0).getMenuName());
    }

    @DisplayName("위치 기준 파라미터 바인딩 테스트")
    @Test
    void testParameterBindingByPosition() {
        String menuName = "한우딸기국밥";
        List<Menu> menus = parameterBindingRepository.selectMenuByBindingPosition(menuName);
        assertEquals(menuName, menus.get(0).getMenuName());
    }
}