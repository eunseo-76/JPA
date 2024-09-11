package com.kenny.jpql.section01.simple;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleJPQLRepositoryTest {

    @Autowired
    SimpleJPQLRepository simpleJPQLRepository;

    @DisplayName("TypedQuery를 이용한 단일행, 단일 컬럼 조회")
    @Test
    void testSelectSingleMenuByTypedQuery() {
        String menuName = simpleJPQLRepository.selectSingleMenuByTypedQuery();
        assertEquals("한우딸기국밥", menuName);
    }

    @DisplayName("Query를 이용한 단일행, 단일 컬럼 조회")
    @Test
    void testSelectSingleMenuByQuery() {
        Object menuName = simpleJPQLRepository.selectSingleMenuByQuery();
        assertEquals("한우딸기국밥", menuName);
    }

    @DisplayName("TypedQuery를 이용한 단일행 조회")
    @Test
    void testSelectSingleRowByTypedQuery() {
        Menu menu = simpleJPQLRepository.selectSingleRowByTypedQuery();
        assertEquals("한우딸기국밥", menu.getMenuName());
    }

    @DisplayName("TypedQuery를 이용한 다중행 조회")
    @Test
    void testSelectMultiRowByTypedQuery() {
        List<Menu> menuList = simpleJPQLRepository.selectMultiRowByTypedQuery();
        assertNotNull(menuList);
        for (Menu menu : menuList) {
            System.out.println(menu + " ");
        }
    }

    @DisplayName("DISTINCT 연산자를 이용한 다중행 조회")
    @Test
    void testSelectUsingDistinct() {
        List<Integer> categoryCodeList = simpleJPQLRepository.selectUsingDistinct();
        System.out.println("categoryCodeList : " + categoryCodeList);
        assertNotNull(categoryCodeList);
    }

    @DisplayName("In 연산자를 이용한 다중행 조회")
    @Test
    void testSelectUsingIn() {
        List<Menu> menuList = simpleJPQLRepository.selectUsingIn();
        assertNotNull(menuList);
        for (Menu menu : menuList) {
            System.out.println(menu);
        }
    }

    @DisplayName("LIKE 연산자를 이용한 다중행 조회")
    @Test
    void testSelectUsingLike() {
        List<Menu> menuList = simpleJPQLRepository.selectUsingLike();
        assertNotNull(menuList);
        for (Menu menu : menuList) {
            System.out.println(menu);
        }
    }
}