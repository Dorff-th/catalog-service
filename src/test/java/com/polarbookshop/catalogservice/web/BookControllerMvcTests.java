package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest     // 스프링 MVC 컴포넌트에 중점을 두고, 명시적으로 BookController 클래스를 타킷으로 하는 테스트 클래스임을 나태난다.
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;    // 모의 환경에서 웹 계층을 테스트하기 위한 유틸리티 클래스

    @MockBean           // 스프링 애플리케이션 콘텍스트에 BookService 의 모의 객체를 추가한다.
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "123412341234";
        given(bookService.viewBookDetail(isbn))
                .willThrow(BookNotFoundException.class);

        mockMvc.perform(get("/book/" + isbn))   //perform()메서드는 요청을 전송하는 역할을 하는 메서드, 여기서는 get 방식으로 요청
                .andExpect(status().isNotFound());  // andExcepct()메서드는 응답을 검증한다. 여기서는 응답코드가 404 NotFound인지 검증한다.
    }
}
