package com.kenny.associationmapping.section03.bidirection;

import jakarta.persistence.*;

@Entity(name = "section03Menu")
@Table(name = "tbl_menu")
public class Menu {

    // 메뉴 엔터티에 카테고리 엔터티를 연관 관계 매핑하고자 한다.

    @Id
    private int menuCode;
    // @Column 어노테이션 생략(yml 파일 참조)
    private String menuName;
    private int menuPrice;
    @ManyToOne  // fk를 가지므로 진짜 연관 관계이다. -> join column 가능
    @JoinColumn(name = "categoryCode")
    private Category category;  // 메뉴 입장에서 카테고리 매핑 (단방향 매핑 1개)
    private String orderableStatus;


    public Menu() {
    }

}