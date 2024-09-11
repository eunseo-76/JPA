package com.kenny.jpql.section06.join;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JoinRepositoryTest {

    @Autowired
    JoinRepository joinRepository;

    @DisplayName("내부 조인 테스트")
    @Test
    void testSelectByInnerJoin() {
        List<Menu> menuList = joinRepository.selectByInnerJoin();
        assertNotNull(menuList);
    }

    @DisplayName("페치 조인 테스트")
    @Test
    void testSelectByFetchJoin() {
        List<Menu> menuList = joinRepository.selectByFetchJoin();
        assertNotNull(menuList);
    }

    @DisplayName("외부 조인 테스트")
    @Test
    void testSelectByOuterJoin() {
        List<Object[]> menuList = joinRepository.selectByOuterJoin();
        assertNotNull(menuList);
        menuList.forEach(
                row -> {
                    for (Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );
    }

    @DisplayName("컬렉션 조인 테스트")
    @Test
    void testSelectByCollectionJoin() {
        List<Object[]> menuList = joinRepository.selectByCollectionJoin();
        assertNotNull(menuList);
        menuList.forEach(
                row -> {
                    for (Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );
    }

    @DisplayName("세타 조인 테스트")
    @Test
    void testSelectByThetaJoin() {
        List<Object[]> menuList = joinRepository.selectByThetaJoin();
        assertNotNull(menuList);
        menuList.forEach(
                row -> {
                    for (Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );
    }


}