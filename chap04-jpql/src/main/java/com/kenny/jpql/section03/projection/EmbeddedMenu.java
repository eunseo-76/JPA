package com.kenny.jpql.section03.projection;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_menu")
public class EmbeddedMenu {
    @Id
    private int menuCode;
    @Embedded
    private MenuInfo menuInfo;  // menuName, menuPrice를 별도의 내장 타입으로 만듦
    private int categoryCode;
    private String orderableStatus;

    public EmbeddedMenu() {}

}