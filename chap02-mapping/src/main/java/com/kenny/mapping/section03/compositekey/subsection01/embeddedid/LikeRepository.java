package com.kenny.mapping.section03.compositekey.subsection01.embeddedid;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository // bean 등록
public class LikeRepository {

    // 의존성 주입
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Like like) {
        entityManager.persist(like);
    }
}
