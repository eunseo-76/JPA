package com.kenny.jpql.section03.projection;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectionService {

    private ProjectionRepository projectionRepository;

    public ProjectionService(ProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }


    // projection repo: jpql 작성 -> jpql을 통해 조회된 엔터티가 반환되게 함
    // service: 트랜잭션 발생 -> 조회된 리스트 중 1번째의 이름을 수정 -> 트랜잭션 종료
    // (반환된 값이 영속성 컨텍스트에서 관리되는 엔터티가 되었는지 확인하기 위해 이름 변경)
    // commit 하면 변화를 감지해서 해당 sql 구문이 flush 됨
    @Transactional
    public List<Menu> singleEntityProjection() {
        List<Menu> menus = projectionRepository.singleEntityProjection();
        menus.get(0).setMenuName("세상에서 제일 맛있는 유니콘 고기");
        return menus;
    }
}
