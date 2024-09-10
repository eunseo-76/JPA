package com.kenny.mapping.section03.compositekey.subsection02.idclass;

import java.io.Serializable;

// 영속성 컨텍스트 내에서 해당 타입이 Id로 처리되기 위해서는 직렬화 처리가 필요하다.
public class CartCompositeKey implements Serializable {

    private int cartOwner;
    private int addedBook;

    public CartCompositeKey() {}

    public CartCompositeKey(int cartOwner, int addedBook) {

        this.cartOwner = cartOwner;
        this.addedBook = addedBook;
    }
}
