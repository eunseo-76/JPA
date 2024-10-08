package com.kenny.jpql.section08.namedquery;

import jakarta.persistence.*;

@Entity(name="Section08Menu")
@Table(name="tbl_menu")
@NamedQueries({
        @NamedQuery(name = "Section08Menu.selectMenuList",
                query = "SELECT m FROM Section08Menu m")    // 정적 쿼리는 미리 저장해두고 불러서 쓴다
})
public class Menu {
    @Id
    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;
    public Menu() {}
    public Menu(
            int menuCode, String menuName, int menuPrice,
            int categoryCode, String orderableStatus
    ) {
        super();
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }
    public int getMenuCode() {
        return menuCode;
    }
    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public int getMenuPrice() {
        return menuPrice;
    }
    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }
    public int getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
    public String getOrderableStatus() {
        return orderableStatus;
    }
    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }
    @Override
    public String toString() {
        return "Menu [menuCode=" + menuCode + ", menuName=" + menuName +
                ", menuPrice=" + menuPrice + ", categoryCode=" + categoryCode +
                ", orderableStatus=" + orderableStatus + "]";
    }
}