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
}
