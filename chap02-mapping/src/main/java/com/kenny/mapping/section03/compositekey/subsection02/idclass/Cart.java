package com.kenny.mapping.section03.compositekey.subsection02.idclass;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cart")
@IdClass(CartCompositeKey.class)    // Id의 타입 지정
public class Cart {

    @Id // IdClass까지 써 주어야 한다.
    @Column(name = "cart_owrner")
    private int cartOwner;  // 카트의 주인(유저 정보)

    @Id
    @Column(name = "added_book")
    private int addedBook;

    @Column(name = "quantity")
    private int quantity;

    // 기본 생성자
    public Cart() {
    }

    // 매개변수 생성자
    public Cart(int cartOwner, int addedBook, int quantity) {
        this.cartOwner = cartOwner;
        this.addedBook = addedBook;
        this.quantity = quantity;
    }
}
