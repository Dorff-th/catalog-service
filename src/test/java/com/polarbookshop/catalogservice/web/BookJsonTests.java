package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.*;

@JsonTest   //JSON  이 직렬화에 중점을 둔 테스트 클래스임을 나태낸다.
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    @DisplayName("JsonPath 형식을 사용해 JSON 객체를 탐색하고 자바의 JSON변환을 확인한다 : 직렬화 확인")
    void testSerialize() throws Exception {
        var book = new Book("1234567890", "Title", "Authoor", 9.99);

        var jsonContext = json.write(book);

        assertThat(jsonContext).extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(jsonContext).extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        assertThat(jsonContext).extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());

        assertThat(jsonContext).extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());

    }

    @Test
    @DisplayName("JSON에서 자바객체로 변환을 확인한다 : 역직렬화")
    void testDeserialize() throws Exception {
        var content = """
                {
                    "isbn" : "1234567890",
                    "title" : "Title",
                    "author" : "Author",
                    "price" : 9.99
                }
                """;

        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Book("1234567890", "Title", "Author", 9.99));
    }
}
