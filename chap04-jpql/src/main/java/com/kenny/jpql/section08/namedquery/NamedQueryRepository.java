package com.kenny.jpql.section08.namedquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NamedQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> selectByDynamicQuery(String searchName, int searchCode) {
        StringBuilder jpql = new StringBuilder("SELECT m FROM Section08Menu m ");
        if(searchName != null && !searchName.isEmpty() && searchCode > 0) {
            jpql.append("WHERE ");
            jpql.append("m.menuName LIKE '%' || :menuName || '%'");
            jpql.append("AND ");
            jpql.append("m.categoryCode = :categoryCode");
        } else {
            if(searchName != null && !searchName.isEmpty()) {
                jpql.append("WHERE ");
                jpql.append("m.menuName LIKE '%' || :menuName || '%'");
            } else if(searchCode > 0) {
                jpql.append("WHERE ");
                jpql.append("m.categoryCode = :categoryCode");
            }
        }
        TypedQuery<Menu> query = entityManager.createQuery(jpql.toString(), Menu.class);
        if(searchName != null && !searchName.isEmpty() && searchCode > 0) {
            query.setParameter("menuName", searchName);
            query.setParameter("categoryCode", searchCode);
        } else {
            if(searchName != null && !searchName.isEmpty()) {
                query.setParameter("menuName", searchName);
            } else if(searchCode > 0) {
                query.setParameter("categoryCode", searchCode);
            }
        }
        List<Menu> menuList = query.getResultList();
        return menuList;
    }

    public List<Menu> selectByNamedQuery() {
        // createNamedQuery : 첫 번째 인자로 전달하는, 이름을 가진 쿼리를 전달하여 수행
        List<Menu> menuList = entityManager.createNamedQuery("Section08Menu.selectMenuList", Menu.class)
                .getResultList();
        return menuList;
    }


    public List<Menu> selectByNamedQueryWithXml(int menuCode) {
        // 인텔리제이에서는 오류라고 빨간 줄을 쳐주지만 구동은 잘 된다
        List<Menu> menuList = entityManager.createNamedQuery("Section08Menu.selectMenuByCode", Menu.class)
                .setParameter("menuCode", menuCode)
                .getResultList();
        return menuList;
    }





}