package com.kenny.associationmapping.section03.bidirection;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BiDirectionService {
    private BiDirectionRepository biDirectionRepository;

    public BiDirectionService(BiDirectionRepository biDirectionRepository) {
        this.biDirectionRepository = biDirectionRepository;
    }

    public Menu findMenu(int menuCode) {
        return biDirectionRepository.findMenu(menuCode);
    }

    @Transactional  // 트랜잭션으로 묶어주지 않으면 findCategory 메소드를 실행한 후 세션이 닫혀버림. 뒤의 내용도 실행하기 위해 트랜잭션으로 묶어줌.
    public Category findCategory(int categoryCode) {
        Category category = biDirectionRepository.findCategory(categoryCode);
        /* 양방향 참조를 구현하면 양방향 그래프 탐색이 가능하다. */
        System.out.println(category.getMenuList());
        System.out.println(category.getMenuList().get(0).getCategory());  // 메뉴 리스트의 특정 메뉴 값을 가져와 카테고리를 탐색
        return category;
    }
    // 양방향 mapping을 한다 : 메뉴에서 카테고리를 가지고, 카테고리에서도 메뉴를 가진다 = 그래프 탐색이 가능하다
}
