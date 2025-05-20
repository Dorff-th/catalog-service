package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController     // 클래스가 스프링 컴포넌트이고 REST 엔드포인트를 위한 핸들러를 제공한다는 것을 표시하는 스테레오타입 애너테이션
@RequestMapping("/books")
public class BookController {

    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //도서 목록 조회
    @GetMapping
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }

    // 특정 isbn 값으로 도서조회
    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable(name = "isbn") String isbn) {
        return bookService.viewBookDetail(isbn);
    }

    //새로운 책 추가
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)     // 책이 성공적으로 생성되면 201 상태코드를 반환한다.
    public Book post(@Valid @RequestBody Book book) {      // @RequestBody는 웹 요청 본문을 메서드 변수로 바인드 한다.
        return bookService.addBookToCatalog(book);
    }

    //기존 책을 수정한다 - isbn이 존재하지 않는다면 새로운 책을 추가한다.
    @PutMapping("{isbn}")
    public Book put(@PathVariable(name = "isbn") String isbn, @Valid @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }



}
