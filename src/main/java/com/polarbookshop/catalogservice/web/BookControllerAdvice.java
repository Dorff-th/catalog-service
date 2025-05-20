package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice   // 클래스가 중앙식 예외 핸들러임을 표시한다.
public class BookControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)  // 이 핸들러가 실행되어야 할 대상인 예외 정의
    @ResponseStatus(HttpStatus.NOT_FOUND)              //예외를 발상핼때 HTTP응답에 포함할 상태코드 정의
    String bookNotFoundHandler(BookNotFoundException ex) {
        return ex.getMessage();     // HTTP 응답 본문에 포함할 메세지
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)    //예외를 발상핼때 HTTP응답에 포함할 상태코드 정의
    String bookAlreadyExistsException(BookAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
