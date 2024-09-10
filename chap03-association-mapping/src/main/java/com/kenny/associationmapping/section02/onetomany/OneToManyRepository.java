package com.kenny.associationmapping.section02.onetomany;

import com.kenny.associationmapping.section01.manytoone.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class OneToManyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Category find(int categoryCode) {
        return entityManager.find(Category.class, categoryCode);
    }
}
