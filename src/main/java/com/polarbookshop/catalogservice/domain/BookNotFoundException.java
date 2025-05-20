package com.polarbookshop.catalogservice.domain;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super(isbn + "ISBN 번호인 책이 존재하지 않습니다.");
    }
}
