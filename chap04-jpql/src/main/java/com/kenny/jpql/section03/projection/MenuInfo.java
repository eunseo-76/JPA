package com.kenny.jpql.section03.projection;

import jakarta.persistence.Embeddable;

@Embeddable
public class MenuInfo {
    private String menuName;
    private int menuPrice;

    @Override
    public String toString() {
        return "MenuInfo{" +
                "menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                '}';
    }
}
