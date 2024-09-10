package com.kenny.mapping.section03.compositekey.subsection01.embeddedid;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LikeBookService {

    private LikeRepository likeRepository;

    public LikeBookService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Transactional
    public void generateLikeBook(LikeDTO likeDTO) {
    // 전달받은 LikeDTO를 기반으로 LIKE 엔터티를 만들어 저장한다.

        Like like = new Like(
                new LikeCompositeKey(
                        likeDTO.getLikedMemberNo(),
                        likeDTO.getLikedBookNo()
                )
        );

        likeRepository.save(like);
    }
}

