package com.polarbookshop.catalogservice.domain;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Set;


public class BookValidationTests {
    private static Validator validator;

    @BeforeAll // 클래스내 테스트를 실행하기전에 가장 먼저 ㅣㄹ행할 코드 블록임을 나타낸다.
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test //   테스트 케이스
    void whenAllFieldCorrectThenValidationSuccessds() {
        var book = new Book("1234567890", "Title", "Author", 9.90); //유효한 ISBN로 책을 생성한다.
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();   // 유효성 검사에서 오류가 없음을 학인한다.
    }


}
