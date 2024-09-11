package com.kenny.jpql.section01.simple;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SimpleJPQLRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public String selectSingleMenuByTypedQuery() {
        // 엔터티에 별칭 필수(as는 생략 가능), 엔터티와 필드 작성
        String jpql = "SELECT m.menuName FROM Section01Menu as m WHERE m.menuCode = 8";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        String resultMenuName = query.getSingleResult();    // 쿼리 실행
        return resultMenuName;
    }

    public Object selectSingleMenuByQuery() {
        // 내부적으로 TypedQuery일 수도, 그냥 Query일 수도 있다. createQuery의 두 번째 인자로 어떤 반환값을 갖는 명시하지 않으면 그냥 Query(타입 지정x)가 된다.
        String jpql = "SELECT m.menuName FROM Section01Menu as m WHERE m.menuCode = 8";
        Query query = entityManager.createQuery(jpql);
        Object resultMenuName = query.getSingleResult();    // 쿼리 실행
        return resultMenuName;
    }

    public Menu selectSingleRowByTypedQuery() {
        // 엔터티에 별칭 필수(as는 생략 가능), 엔터티와 필드 작성
        String jpql = "SELECT m FROM Section01Menu as m WHERE m.menuCode = 8";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class); // 반환값이 메뉴라는 엔터티 그 자체가 된다
        Menu resultMenu = query.getSingleResult();    // 쿼리 실행
        return resultMenu;
    }

    public List<Menu> selectMultiRowByTypedQuery() {
        // 엔터티에 별칭 필수(as는 생략 가능), 엔터티와 필드 작성
        String jpql = "SELECT m FROM Section01Menu as m";   // 해당 엔터티를 모두 조회
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class); // 한 행이 menu로 mapping 된다. 반환값이 메뉴라는 엔터티 그 자체가 된다
        List<Menu> resultMenuList = query.getResultList();    // 쿼리 실행
        return resultMenuList;
    }

    public List<Integer> selectUsingDistinct() {
        String jpql = "SELECT DISTINCT m.categoryCode FROM Section01Menu as m";   // 중복 제거(동일 카테고리 코드 제외)
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class); // 카테고리 코드(int)가 여러 행이므로 integer list
        List<Integer> resultMenuList = query.getResultList();    // 쿼리 실행
        return resultMenuList;
    }

    // 11, 12 카테고리 코드를 가진 메뉴 목록 조회
    public List<Menu> selectUsingIn() {
        String jpql = "SELECT m FROM Section01Menu as m WHERE m.categoryCode IN (11, 12)";
        List<Menu> resultMenuList = entityManager.createQuery(jpql, Menu.class).getResultList();    // 이거나 아래나 동일
//        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);
//        List<Menu> resultMenuList = query.getResultList();
        return resultMenuList;
    }
    // "마늘" 이라는 문자열이 메뉴명에 포함되는 메뉴 목록 조회
    public List<Menu> selectUsingLike() {
        String jpql = "SELECT m FROM Section01Menu as m WHERE m.menuName LIKE '%마늘%'";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);
        List<Menu> resultMenuList = query.getResultList();
        return resultMenuList;
    }

}

