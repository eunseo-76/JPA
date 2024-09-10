package com.kenny.mapping.section03.compositekey.subsection01.embeddedid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

// 복합키 클래스 : 좋아요 엔터티에 내장됨. 엔터티가 아니라 내장 가능한 클래스이다.
@Embeddable
public class LikeCompositeKey implements Serializable {
    // 영속성 컨텍스트에서 관리된다.
    // 이 때 pk 값이 타입일 경우 Serializable을 구현해야 한다.

    @Column(name = "liked_member_no")
    private int likedMemberNo;

    @Column(name = "liked_book_no")
    private int likedBookNo;

    // 생성자
    public LikeCompositeKey() {}

    public LikeCompositeKey(int likedMemberNo, int likedBookNo) {
        this.likedMemberNo = likedMemberNo;
        this.likedBookNo = likedBookNo;
    }
}
