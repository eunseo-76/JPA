package com.kenny.jpql.section05.groupfunction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupFunctionRepositoryTest {

    @Autowired
    GroupFunctionRepository groupFunctionRepository;

    @DisplayName("특정 카테고리에 해당하는 메뉴 수 조회")
    @Test
    void testCountMenuOfCategory() {
        int categoryCode = 777; // 777은 없는 값
        long countOfMenu = groupFunctionRepository.countMenuOfCategory(categoryCode);
        assertTrue(countOfMenu >= 0);
        System.out.println(countOfMenu);
    }
    // count는 없는 값을 조회 시 0 반환

    // sum은 없는 값 조회 시 null 반환
    @DisplayName("특정 카테고리에 해당하는 메뉴 가격 합 조회")
    @Test
    void testSumMenuPriceOfCategory() {
        int categoryCode = 777; // 777은 없는 값
//        long sumMenuPrice = groupFunctionRepository.sumMenuPriceOfCategory(categoryCode);
//        assertTrue(sumMenuPrice >= 0);
        // Long wrapper 클래스 반환 하기 때문에 null 값이 나올 수 있도록
        assertDoesNotThrow(
                () -> {
                    Long sumMenuPrice = groupFunctionRepository.sumMenuPriceOfCategory(categoryCode);
                    System.out.println("sumMenuPrice = " + sumMenuPrice);
                }
        );
    }
}