package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(

        @NotBlank(message = "The Book ISBN must be defined")
        @Pattern(regexp = "(^[0-9]{10}|[0-9]{13})$", message = "The ISBN format be valid.")
        String isbn,

        @NotBlank(message = "The book title must be defiend")   // 이 필드는 널 값이 되어서는 안되고 화이트 스페이스가 아닌 문자를 최소 하나 이상 있어야 한다.
        String title,

        @NotBlank(message = "The author author must be defiend")
        String author,

        @NotNull(message = "The author price must be defiend")
        @Positive(message = "책 가격은 0보다 커야 한다.")
        Double price
) {
}
