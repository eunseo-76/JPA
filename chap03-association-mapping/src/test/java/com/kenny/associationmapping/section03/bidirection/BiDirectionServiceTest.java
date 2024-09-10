package com.kenny.associationmapping.section03.bidirection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BiDirectionServiceTest {

    @Autowired
    private BiDirectionService biDirectionService;

    @DisplayName("양방향 연관 관계 매핑 조회 테스트1(연관 관계의 주인")
    @Test
    void biDirectionFindTest1() {
        int menuCode = 9;
        Menu foundMenu = biDirectionService.findMenu(menuCode);
        assertNotNull(foundMenu);
    }

    @DisplayName("양방향 연관 관계 매핑 조회 테스트2(연관 관계의 주인이 아님")
    @Test
    void biDirectionFindTest2() {
        int categoryCode = 4;
        Category foundCategory = biDirectionService.findCategory(categoryCode);
        assertNotNull(foundCategory);
    }
}