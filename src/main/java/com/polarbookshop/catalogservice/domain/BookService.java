package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

@Service        // 이 클래스는 스프링이 관리하는 서비스라는 것을 표시하는 스테레오타입 애너테이션
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;   // BookRepository를 생성자 오토와이어링을 통해 제공
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetail(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));    // isbn 으로 책을 조회하고  isbn에 해당하는 책이 없다면 예외 발생
    }

    public Book addBookToCatalog(Book book) {
        // 동일한 책을 여러번 추가하려고 시도하면 그에 해당하는 예외를 발생시킨다(새로운 책을 추가할때 이미 동알한 isbn번호를 가진 책이 있다면 예외발생)
        if(bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }

        return bookRepository.save(book);
    }

    public void removeBookFromCaltalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existsBook-> {
                   var bookToUpdate = new Book(existsBook.isbn(),       // 책을 수정할 때 식별자인 ISBN 코드를 제외한 모든 필드를 수정할 수 있다.
                           book.title(),
                           book.author(),
                           book.price()
                           ) ;
                   return bookRepository.save(bookToUpdate);
                }).orElseGet(()-> addBookToCatalog(book));  // 카탈로그에 존재하지 않는 책을 수정하려고 하면 새로운 책을 만든다.
    }

}
