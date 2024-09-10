package com.kenny.associationmapping.section03.bidirection;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "section03Category")
@Table(name = "tbl_category")
public class Category {

    // 카테고리 엔터티가 메뉴 엔터티를 참조한다.

    @Id
    private int categoryCode;   // pk 이자 fk
    private String categoryName;
    private Integer refCategoryCode;
    /* 양방향 연관 관계를 맺을 경우 FK를 가진 엔터티를 진짜,
    * FK를 가지지 않는 엔터티를 가짜 연관 관계라고 본다.
    * 진짜 연관 관계는 똑같이 처리하지만 가짜 연관 관계는
    * mappedBy 속성에 진짜 연관 관계 필드명을 넣어서 설정한다. */
    @OneToMany(mappedBy = "category")   // 가짜 연관 관계 -> 진짜 연관 관계를 맺고 있는 필드 작성
    private List<Menu> menuList;    // 카테고리 입장에서 메뉴 매핑 (단방향 매핑 1개)
    // 양방향 매핑 시, 누가 진짜 연관 관계인지 고려해야 한다.
    // 진짜 연관 관계 도출하는 방법 : fk를 가지고 있는 객체가 진짜
    public Category() {
    }

}
