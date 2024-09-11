package com.kenny.jpql.section06.join;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity(name = "Section06Category")
@Table(name = "tbl_category")
public class Category {

    @Id
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;
    @OneToMany(mappedBy = "category")   // 양방향 연관 관계에서 연관 관계의 주인(FK 가짐)은 mappedBy = ""
    private List<Menu> menuList;
}
