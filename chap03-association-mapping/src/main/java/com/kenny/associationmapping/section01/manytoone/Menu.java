package com.kenny.associationmapping.section01.manytoone;

import jakarta.persistence.*;

@Entity(name = "section01Menu")
@Table(name = "tbl_menu")
public class Menu {

    // 메뉴 엔터티에 카테고리 엔터티를 연관 관계 매핑하고자 한다.

    @Id
    private int menuCode;
    // @Column 어노테이션 생략(yml 파일 참조)
    private String menuName;
    private int menuPrice;
    /* 영속성 전이 : 특정 엔터티를 영속화할 때 연관된 엔터티도 함께 영속화 한다는 의미이다. */
    /* FetchTyp.EAGER : 즉시 로딩, FetchType.LAZY : 지연 로딩
    * @ManyToOne 어노테이션의 기본 속성은 즉시 로딩 (한 번에 join 해서 처리) */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)  // 다중성 명시 (메뉴 기준 : N:1 연관관계)
    @JoinColumn(name = "categoryCode")  // categoryCode(fk)와 Join 한다. (CamelCase로 작성한다. yml 파일 참조)
    private Category category;
    private String orderableStatus;

    // DTO와 달리 엔터티는 DB와 그대로 매핑이 되기 때문에 마구 변경이 되면 안 된다.
    // 그래서 setter 메소드는 작성하지 않는다. (DB가 변경될 위험성 때문)
    // getter 메소드는 현재 있는 값을 알아오는 것이므로 크게 문제되지 않는다.


    public Menu() {
    }

    public Menu(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    public Category getCategory() {
        return category;
    }
}
