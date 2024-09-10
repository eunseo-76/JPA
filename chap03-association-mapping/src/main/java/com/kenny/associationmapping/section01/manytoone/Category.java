package com.kenny.associationmapping.section01.manytoone;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "section01Category")
@Table(name = "tbl_category")
public class Category {

    @Id
    private int categoryCode;   // pk 이자 fk
    private String categoryName;
    private Integer refCategoryCode;

    public Category() {
    }

    // 매개 변수 생성자가 있으면 기본 생성자도 있어야
    public Category(int categoryCode, String categoryName, Integer refCategoryCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
    }
}
