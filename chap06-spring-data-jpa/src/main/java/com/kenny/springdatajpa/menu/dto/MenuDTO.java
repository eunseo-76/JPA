package com.kenny.springdatajpa.menu.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok
@Getter
@Setter
@ToString
public class MenuDTO {
    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;
}
