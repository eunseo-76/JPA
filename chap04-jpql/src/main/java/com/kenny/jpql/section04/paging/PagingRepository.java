package com.kenny.jpql.section04.paging;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PagingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> usingPagineAPI(int offset, int limit) {
        String jpql = "SELECT m FROM Section04Menu m ORDER BY m.menuCode DESC"; // 메뉴 코드를 최신순으로 엔터티 자체를 조회

        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class)
                // offset : 몇 개를 뛰어넘는다. 총 100건을 조회할 때, 10건씩 잘라서 1, 2, 3,.. 10 페이지를 제공.
                // offset = 10 이면 0~9는 뛰어넘고 10부터 조회
                .setFirstResult(offset) // 조회를 시작할 위치 (0부터)
                .setMaxResults(limit)   // 조회할 데이터의 개수 (최대한의 개수)
                .getResultList();
        return menuList;
    }
}
