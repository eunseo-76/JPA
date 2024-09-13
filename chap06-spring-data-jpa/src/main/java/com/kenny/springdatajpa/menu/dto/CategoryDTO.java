package com.kenny.springdatajpa.menu.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


// lombok
@Getter
@Setter
@ToString
    public class CategoryDTO {
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;
}
