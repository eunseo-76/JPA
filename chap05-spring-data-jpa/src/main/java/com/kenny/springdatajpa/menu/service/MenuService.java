package com.kenny.springdatajpa.menu.service;

import com.kenny.springdatajpa.menu.dto.CategoryDTO;
import com.kenny.springdatajpa.menu.dto.MenuDTO;
import com.kenny.springdatajpa.menu.entity.Category;
import com.kenny.springdatajpa.menu.entity.Menu;
import com.kenny.springdatajpa.menu.repository.CategoryRepository;
import com.kenny.springdatajpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service    // bean 등록
public class MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    // 메뉴 repo를 생성자를 통해 주입 받는다.
    public MenuService(MenuRepository menuRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
        this.modelMapper = new ModelMapper();
        this.categoryRepository = categoryRepository;
    }

    /* Entity to DTO 작업이 필요하다. */
    // 엔터티는 DB와 직접 연결이 되어 있음. 이 객체는 클라이언트에게 노출하지 않아야 함.
    // 엔터티에 있는 내용을, 내가 필요한 형태의 클래스로 변화시켜 반환하는 작업이 필요하다.
    // Entity -> DTO, DTO -> Entity 로 변환하는 작업이 Service에서 이루어진다.

    /* 1. 수동 매핑 메소드 작성 (toEntity, fromEntty, toDTO, fromDTO ...)
     *  2. 자동 매핑 라이브러리 사용
     *  (취향 차이) */

    /* 1. findById */
    public MenuDTO findMenuByMenuCode(int menuCode) {
        // 조회된 엔터티를 DTO로

        // findById: Id 값 전달해서 조회. Optional 값 반환(null인 경우 handling 필요)
        Menu menu = menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);

        // menu 엔터티에 있는 필드 값들만 꺼내서 menu DTO 타입으로 변환
        return modelMapper.map(menu, MenuDTO.class);
    }

    /* 2. findAll(Sort)  */
    public List<MenuDTO> findMenuList() {

        List<Menu> menuList = menuRepository.findAll(Sort.by("menuCode").descending());

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .toList();
    }

    /* 3. findAll(Pageable) */
    public Page<MenuDTO> findMenuList(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("menuCode").descending()
        );
        // page 라는 별도의 타입으로 반환
        Page<Menu> menuList = menuRepository.findAll(pageable);
        // page는 map 메소드를 가짐
        return menuList.map(menu -> modelMapper.map(menu, MenuDTO.class));
    }

    /* Query Method  */
    public List<MenuDTO> findByMenuPrice(Integer menuPrice) {
//        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice);
//        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThanOrderByMenuPrice(menuPrice);
        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice, Sort.by("menuCode").descending());

        return menuList.stream()
                .map (menu -> modelMapper.map(menu, MenuDTO.class))
                .toList();
    }

    /* 3. JPQL or Native Query 를 직접 써야 하는 경우 */
    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
    }

    /* 6. save */
    @Transactional
    public void registMenu(MenuDTO menuDTO) {

        menuRepository.save(modelMapper.map(menuDTO, Menu.class));

    }

    /* 7. 수정 */
    // 수정 메소드는 따로 없다.
    // 수정 = 엔터티가 가지고 있는 필드 값을 바꾼다 = 엔터티의 값을 변경한다
    @Transactional
    public void modifyMenu(MenuDTO menuDTO) {

        // 조회가 되는 값이 없으면 exception 발생시킴
        Menu foundMenu = menuRepository.findById(menuDTO.getMenuCode()).orElseThrow(IllegalArgumentException::new);
        // setter 사용 지양, 기능에 맞는 메소드를 별도로 정의해서 사용하기
        foundMenu.modifyMenuName(menuDTO.getMenuName());
    }

    /* 8. 삭제 */
    @Transactional
    public void deleteMenu(Integer menuCode) {

        menuRepository.deleteById(menuCode);
    }
}
