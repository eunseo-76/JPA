package com.kenny.jpql.section03.projection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProjectionTest {

    @Autowired
    ProjectionService projectionService;

    @Autowired
    ProjectionRepository projectionRepository;

    @DisplayName("단일 엔터티 프로젝션")
    @Test
    void testSingleEntityProjection() {
        List<Menu> menus = projectionService.singleEntityProjection();
        assertNotNull(menus);
    }

    @DisplayName("임베디드 타입 프로젝션")
    @Test
    void testEmbeddedTYpeProjection() {
        List<MenuInfo> menuInfos = projectionRepository.embeddedTypeProjection();
        assertNotNull(menuInfos);
        for (MenuInfo menuInfo : menuInfos) {
            System.out.println(menuInfo);
        }
    }

    @DisplayName("스칼라 타입 프로젝션")
    @Test
    void testScalarTypeProjection() {
        List<Object[]> categoryList = projectionRepository.scalarTypeProjection();
        assertNotNull(categoryList);
        categoryList.forEach(   // 켁 이렇게 어렵게 출력해야됨?
                row -> {
                    for (Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );
    }

    @DisplayName("new 명령어 프로젝션")
    @Test
    void testNewCommandProjection() {
        List<CategoryInfo> categoryInfos = projectionRepository.newCommandProjection();
        assertNotNull(categoryInfos);
        categoryInfos.forEach(System.out::println);
    }
}
