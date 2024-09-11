package com.kenny.jpql.section03.projection;

public class CategoryInfo {

    private int categoryCode;
    private String categoryName;

    public CategoryInfo() {
    }

    public CategoryInfo(int categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryInfo{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
