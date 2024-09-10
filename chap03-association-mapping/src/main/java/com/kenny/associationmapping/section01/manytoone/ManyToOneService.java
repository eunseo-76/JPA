package com.kenny.associationmapping.section01.manytoone;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ManyToOneService {

    private ManyToOneRepository manyToOneRepository;

    public ManyToOneService(ManyToOneRepository manyToOneRepository) {
        this.manyToOneRepository  = manyToOneRepository;
    }

    public Menu findMenu(int menuCode) {
        return manyToOneRepository.find(menuCode);
    }

    public String findCategoryNameByJpql(int menuCode) {
        return manyToOneRepository.findCategoryName(menuCode);
    }

    @Transactional  // 저장이므로 트랜잭션
    public void registMenu(MenuDTO newMenu) {

        Menu menu = new Menu(
                newMenu.getMenuCode(),
                newMenu.getMenuName(),
                newMenu.getMenuPrice(),
                new Category(
                        newMenu.getCategory().getCategoryCode(),
                        newMenu.getCategory().getCategoryName(),
                        newMenu.getCategory().getRefCategoryCode()
                ),
                newMenu.getOrderableStatus()
        );

        manyToOneRepository.regist(menu);
    }
}