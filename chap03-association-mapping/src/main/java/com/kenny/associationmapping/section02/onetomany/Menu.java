package com.kenny.associationmapping.section02.onetomany;

import jakarta.persistence.*;

@Entity(name = "section02Menu")
@Table(name = "tbl_menu")
public class Menu {

    // 메뉴 엔터티에 카테고리 엔터티를 연관 관계 매핑하고자 한다.

    @Id
    private int menuCode;
    // @Column 어노테이션 생략(yml 파일 참조)
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;


    public Menu() {
    }

}