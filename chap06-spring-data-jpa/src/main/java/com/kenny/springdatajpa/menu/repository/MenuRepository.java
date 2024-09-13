package com.kenny.springdatajpa.menu.repository;

import com.kenny.springdatajpa.menu.entity.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 내가 원하는 엔터티를 다루는 repo에 jpa repo를 다시 구현
// <엔터티 타입, 엔터티가 다뤄질 때 사용할 id 속성>
// 기본 crud 기능이 모두 내포되어 있으므로 작성할 필요가 없다.
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    /* 파라미터로 전달 받은 가격을 초과하는 메뉴 목록 조회 */
    List<Menu> findByMenuPriceGreaterThan(Integer menuPrice);

    /* 파라미터로 전달 받은 가격을 초과하는 메뉴 목록 조회 + 가격순 조회 */
    List<Menu> findByMenuPriceGreaterThanOrderByMenuPrice(Integer menuPrice);

    /* 파라미터로 전달 받은 가격을 초과하는 메뉴 목록 조회 + 전달받은 정렬 기준*/
    List<Menu> findByMenuPriceGreaterThan(Integer menuPrice, Sort sort);


}
