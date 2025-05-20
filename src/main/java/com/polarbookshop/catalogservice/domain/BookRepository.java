package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

public interface BookRepository {
    Iterable<Book> findAll();       // 카탈로그내 모든 도서를 조회한다.
    Optional<Book> findByIsbn(String isbn); // 주어진  ISBN을 갖는 도서를 가져온다.
    boolean existsByIsbn(String isbn);  //  ISBN 번호가 존재하는지 확인
    Book save(Book book);   // 카탈로그에 새 도서를 추가한다. 또는 수정한다
    void deleteByIsbn(String isbn);     // 주어진 ISBN을 갖는 도서를 삭제한다.
}
