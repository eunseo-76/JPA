package com.kenny.springdatajpa.common;

import org.springframework.data.domain.Page;


// 전체 18 페이지가 있다고 가정
// 현재 요청하는 페이지(예시: 11) 기준 <11 12 ... 18>
// 18 < 20 이면 end page가 18로 바뀐다
public class Pagenation {
    public static PagingButton getPagingButtonInfo(Page page) {
        int currentPage = page.getNumber() + 1;
        int defaultButtonCount = 10;
        int startPage
                = (int) (Math.ceil((double) currentPage / defaultButtonCount) - 1)
                * defaultButtonCount + 1;
        int endPage = startPage + defaultButtonCount - 1;
        if(page.getTotalPages() < endPage) endPage = page.getTotalPages();
        if(page.getTotalPages() == 0 && endPage == 0) endPage = startPage;
        return new PagingButton(currentPage, startPage, endPage);
    }
}
