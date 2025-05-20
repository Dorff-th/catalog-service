package com.polarbookshop.catalogservice.domain;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String isbn) {
        super("책 ISBN : " + isbn + "가 이미 존재합니다 ");
    }
}
