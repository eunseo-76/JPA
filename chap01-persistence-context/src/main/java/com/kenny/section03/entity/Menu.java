package com.kenny.section03.entity;

import jakarta.persistence.*;

// Menu : JPA에서 다루는 DB와 매핑될 수 있는 '엔터티' (클래스)
// 엔터티는 반드시 어떤 필드가 primary key인지 설정해야 함.
@Entity(name = "Section03Menu") // 이름 생략 시 class명과 동일. 중복 entity 명칭 불가
@Table(name = "tbl_menu")  // 매핑될 테이블 설정
public class Menu {

    @Id // 어떤 필드가 primary key인지 설정 (필수)
    @Column(name = "menu_code") // 이 필드가 어떤 컬럼과 매핑되어야 하는지
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // 이게 있으면 테스트가 잘 안 돼서 제외
    private int menuCode;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_price")
    private int menuPrice;
    @Column(name = "category_code")
    private int categoryCode;
    @Column(name = "orderable_status")
    private String orderableStatus;

    // 엔터티는 DTO 처럼 생성자, getter,setter 모두 만들 필요는 없다.
    // 테스트를 위해 필요한 것만 추가함.

    // JPA에서 엔터티를 다루려면 기본 생성자가 필요하다.
    public Menu() {
    }

    public int getMenuCode() {
        return menuCode;
    }

    public Menu(String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public Menu(int menuCode, String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }


}
