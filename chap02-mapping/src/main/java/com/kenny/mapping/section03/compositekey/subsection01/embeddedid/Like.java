package com.kenny.mapping.section03.compositekey.subsection01.embeddedid;

// 좋아요 엔터티(클래스)

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_like")
public class Like {

    @EmbeddedId // 복합키에 대한 표시. @Embedded가 아니라 @EmbeddedId
    private LikeCompositeKey likeInfo;

    public Like() {}

    public Like(LikeCompositeKey likeInfo) {
        this.likeInfo = likeInfo;
    }
}
