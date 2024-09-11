package com.kenny.jpql.section06.join;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JoinRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // InnerJoin : 카테고리 값이 같은 경우 반환
    public List<Menu> selectByInnerJoin() {
        String jpql = "SELECT m FROM Section06Menu m JOIN m.category c";
        return entityManager.createQuery(jpql, Menu.class).getResultList();
    }
    // 지연 로딩 때문에 쿼리가 의도한 것보다 많이 반복된다

    // InnerJoin : 카테고리 값이 같은 경우 반환
    public List<Menu> selectByFetchJoin() {
        /* Fetch : JPQL 에서 성능 최적화를 위해 사용하는 기능으로
        * 연관된 엔터티나 컬렉션을 한 번에 조회한다. (지연 로딩이 아닌 즉시 로딩을 수행한다) */
        String jpql = "SELECT m FROM Section06Menu m JOIN FETCH m.category c";
        return entityManager.createQuery(jpql, Menu.class).getResultList();
    }

    public List<Object[]> selectByOuterJoin() {
        String jpql = "SELECT m.menuName, c.categoryName FROM Section06Menu m RIGHT JOIN m.category c";
        return entityManager.createQuery(jpql).getResultList();
    }

    public List<Object[]> selectByCollectionJoin() {
        // c.menuList : collection
        // 카테고리라는 객체에서 menuList라는 필드가 기준이 된다고 생각
        String jpql = "SELECT c.categoryName, m.menuName FROM Section06Category c LEFT JOIN c.menuList m";
        return entityManager.createQuery(jpql).getResultList();
    }

    // cross join 이라고도 함
    // c * m (테이블 안의 모둔 결과가 나옴)
    public List<Object[]> selectByThetaJoin() {
        String jpql = "SELECT c.categoryName, m.menuName FROM Section06Category c, Section06Menu m";
        return entityManager.createQuery(jpql).getResultList();
    }

}
