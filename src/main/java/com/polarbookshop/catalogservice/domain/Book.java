package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(

        @NotBlank(message = "ISBN은 필수입니다.")
        @Pattern(regexp = "(^[0-9]{10}|[0-9]{13})$", message = "ISBN은 10자리 혹은 13자리 숫자로 입력하세요.")
        String isbn,

        @NotBlank(message = "책 제목은 반드시 입력해야 합니다.")   // 이 필드는 널 값이 되어서는 안되고 화이트 스페이스가 아닌 문자를 최소 하나 이상 있어야 한다.
        String title,

        @NotBlank(message = "책 저자는 반드시 입력해야 합니다.")
        String author,

        @NotNull(message = "책 가격은 반드시 입력해야 합니다.")
        @Positive(message = "책 가격은 0보다 커야 한다.")
        Double price
) {
}
