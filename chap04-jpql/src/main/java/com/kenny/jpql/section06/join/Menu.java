package com.kenny.jpql.section06.join;

import jakarta.persistence.*;

@Entity(name="Section06Menu")
@Table(name="tbl_menu")
public class Menu {
    @Id
    private int menuCode;
    private String menuName;
    private int menuPrice;
    @ManyToOne  // 메뉴 입장에서 ManyToOne : 하나의 카테고리에 여러 메뉴 발생
    @JoinColumn(name = "categoryCode")  // FK
    private Category category;
    private String orderableStatus;

    public Menu() {}
}