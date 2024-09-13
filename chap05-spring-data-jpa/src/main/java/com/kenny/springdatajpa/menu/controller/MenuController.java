package com.kenny.springdatajpa.menu.controller;

import com.kenny.springdatajpa.common.Pagenation;
import com.kenny.springdatajpa.common.PagingButton;
import com.kenny.springdatajpa.menu.dto.CategoryDTO;
import com.kenny.springdatajpa.menu.dto.MenuDTO;
import com.kenny.springdatajpa.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
@Slf4j  // 로깅 라이브러리. 내부적인 구현은 log4j(초기), logback이 될 수 있다.
public class MenuController {

    private final MenuService menuService;  // 조회 요청 위해 메뉴 서비스로 의존성 주입
    private final PageableHandlerMethodArgumentResolverCustomizer pageableCustomizer;

    public MenuController(MenuService menuService, PageableHandlerMethodArgumentResolverCustomizer pageableCustomizer) {
        this.menuService = menuService;
        this.pageableCustomizer = pageableCustomizer;
    }

    @GetMapping("/{menuCode}")
    // pathVariable로 넘어온 값 꺼내기
    public String findMenuByCode(@PathVariable int menuCode, Model model) {
        MenuDTO resultMenu = menuService.findMenuByMenuCode(menuCode);
        model.addAttribute("menu", resultMenu); // 조회된 값 담기
        return "menu/detail";
    }

    @GetMapping("/list")
    public String findMenuList(Model model, @PageableDefault Pageable pageable) {
        // menuDTO를 목록으로 전달하기 위함

        /* 페이징 처리 이전 단순 정렬 */
//        List<MenuDTO> menuList = menuService.findMenuList();
//        model.addAttribute("menuList", menuList);

        /* 페이징 처리 포함 */
        // 개발 단계에서 확인을 위해 sout 이런 걸 쓰는 건 괜찮지만, 배포 단계에서도 쓰면 리소스를 많이 잡아먹음.
        // 그래서 로그를 찍는다.
        /* 페이징 처리 포함 */
        log.info("pageable : {}", pageable);
        Page<MenuDTO> menuList = menuService.findMenuList(pageable);
        log.info("{}", menuList.getContent());
        log.info("{}", menuList.getTotalPages());
        log.info("{}", menuList.getTotalElements());
        log.info("{}", menuList.getSize());
        log.info("{}", menuList.getNumberOfElements());
        log.info("{}", menuList.isFirst());
        log.info("{}", menuList.isLast());
        log.info("{}", menuList.getSort());
        log.info("{}", menuList.getNumber());

        PagingButton paging = Pagenation.getPagingButtonInfo(menuList);

        model.addAttribute("menuList", menuList);
        model.addAttribute("paging", paging);

        return "menu/list";
    }

    @GetMapping("/querymethod")
    public void queryMethodPage() {
    }

    @GetMapping("/search")
    public String findByMenuPrice(@RequestParam Integer menuPrice, Model model) {

        List<MenuDTO> menuList = menuService.findByMenuPrice(menuPrice);
        model.addAttribute("menuList", menuList);

        return "menu/searchResult";
    }

    @GetMapping("/regist")
    public void registPage() {}

    @GetMapping("/category")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }

    @PostMapping("/regist")
    public String registMenu(@ModelAttribute MenuDTO menuDTO) {

        menuService.registMenu(menuDTO);
        return "redirect:/menu/list";
    }

    @GetMapping("/modify")
    public void modifyPage() {
    }

    @PostMapping("/modify")
    public String modifyMenu(@ModelAttribute MenuDTO menuDTO) {
        menuService.modifyMenu(menuDTO);
        // 수정된 메뉴 응답
        return "redirect:/menu/" + menuDTO.getMenuCode();
    }

    @GetMapping("/delete")
    public void deletePage() {
    }

    @PostMapping("/delete")
    public String deleteMenu(@RequestParam Integer menuCode) {
        menuService.deleteMenu(menuCode);
        return "redirect:/menu/list";   // 전체 목록으로 돌아가기
    }
}

// 이 예제는 SSR(Server Side Rendering)으로 구현하였다.
// 클라이언트로부터 요청을 받은 서버는 완성된 HTML 을 응답으로 돌려주고, 브라우저에서 이를 해석하여 보여준다.
// 응답이 완성되었다는 것은, 서버 측에서 결과를 렌더링하여 넘겨준다는 의미.

// CSR(Client Side Rendring)은 완성된 HTML 이 아니라 데이터(XML, json)를 넘겨준다.
// 서버 측에서 렌더링하지 않고 현재 필요한 데이터를 json 형식으로 주고, 클라이언트가 데이터를 화면에 렌더링한다.

// SSR : String(논리적인 view의 이름), void(생략. 요청 값=논리직인 view 의미), ModelAndView(논리적인 view를 넣음)를 이용하였다.
// findCategoryList()는 @ResponseBody를 이용