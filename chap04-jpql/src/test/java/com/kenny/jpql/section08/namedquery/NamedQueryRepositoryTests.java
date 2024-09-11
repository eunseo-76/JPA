package com.kenny.jpql.section08.namedquery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NamedQueryRepositoryTests {

    @Autowired
    private NamedQueryRepository namedQueryRepository;

    @DisplayName("동적쿼리를 이용한 조회 테스트")
    @Test
    public void testSelectByDynamicQuery() {
        //given
        String searchName = "마늘";
        int searchCode = 4;
        //when
        List<Menu> menuList
                = namedQueryRepository.selectByDynamicQuery(searchName, searchCode);
        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @DisplayName("NamedQuery 이용한 조회 테스트")
    @Test
    public void testSelectNamedQuery() {
        //given
        //when
        List<Menu> menuList = namedQueryRepository.selectByNamedQuery();
        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @DisplayName("NamedQuery XML 파일 이용한 조회 테스트")
    @Test
    public void testSelectNamedQueryWithXml() {
        //given
        int menuCode = 3;
        //when
        List<Menu> menuList = namedQueryRepository.selectByNamedQueryWithXml(menuCode);
        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

}