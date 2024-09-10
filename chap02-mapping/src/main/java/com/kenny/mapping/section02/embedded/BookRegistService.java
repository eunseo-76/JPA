package com.kenny.mapping.section02.embedded;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BookRegistService {

    // 의존성 주입
    private BookRepository bookRepository;

    public BookRegistService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void registBook(BookRegistDTO newBook) {
        // 컨트롤러 계층에서 넘어온 객체는 엔터티가 아님. DTO로 변환하는 과정이 필요하다.

        Book book = new Book(
                newBook.getBookTitle(),
                newBook.getAuthor(),
                newBook.getPublisher(),
                newBook.getPublishedDate(),
                new Price(  // 전달된 객체를 Price 타입으로
                        newBook.getRegularPrice(),
                        newBook.getDiscountRate()
                )
        );

        // 변환된 결과를 save 한다.
        bookRepository.save(book);
    }
}
