package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT  // 임의의 포트 번호를 사용하는 서블릿 컨테이너로 애플리케이션 콘텍스트를 생성한다.
)
public class CatalogServiceApplicationTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenPostRequestThenBookCreated() {
        var expectedBook = new Book("1231231234", "Title", "Author", 9.11);

        webTestClient
                .post()
                .uri("/books") // /books 엔드포인트로 post 요청을 보낸다.
                .bodyValue(expectedBook)    // 본문 요청에 expectedBook 객체를 추가한다.
                .exchange()     // 요청을 전송한다.
                .expectStatus().isCreated() //HTTP 응답이 201 생성 상태를 갖는지 확인한다
                .expectBody(Book.class).value(actualBook ->{
                    assertThat(actualBook).isNotNull();     // null 이 아닌지 확인
                    assertThat(actualBook.isbn())
                            .isEqualTo(expectedBook.isbn());    //
                });
    }
}
