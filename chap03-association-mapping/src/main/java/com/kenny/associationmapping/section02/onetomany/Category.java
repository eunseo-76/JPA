package com.kenny.associationmapping.section02.onetomany;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "section02Category")
@Table(name = "tbl_category")
public class Category {

    // 카테고리 엔터티가 메뉴 엔터티를 참조한다.

    @Id
    private int categoryCode;   // pk 이자 fk
    private String categoryName;
    private Integer refCategoryCode;

    /* @OneToMany의 경우 default FetchType.LAZY로 설정되어 있다.
    * 따라서 즉시 로딩이 필요한 경우 별도로 EAGER 설정해주어야 한다. */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryCode")  // pk
    private List<Menu> menuList;

    public Category() {
    }

}
