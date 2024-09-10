package com.kenny.mapping.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository // bean 등록
public class MemberRepository {

    @PersistenceContext // spring 환경에서 entityManager를 의존성 주입 받음 -> 이 객체에 대한 의존성 주입이 자동으로 일어남
    private EntityManager entityManager;

    public void save(Member member) {
        entityManager.persist(member);
    }

    public String findNameById(String memberId) {
        // JPQL : JPA 에서 제공하는 객체 지향적 SQL 구문. 대상이 테이블이 아니라 엔터티. 만들어놓은 Member 엔터티를 대상으로 select 구문 작성
        // SELECT 필드명 FROM 엔터티이름 별칭 WHERE 필드명 (테이블이 아니라 필드명이 들어간다. 별칭은 필수이다.)
        String jpql = "SELECT m.memberName FROM entityMember m WHERE m.memberId = '" + memberId + "'";
        return entityManager.createQuery(jpql, String.class).getSingleResult();
    }

}
