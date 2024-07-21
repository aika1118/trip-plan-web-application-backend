package com.wskang.trip.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

// 전역 예외 처리기
@ControllerAdvice
public class GlobalExceptionHandler {

    // DB 검색 시 값이 없는 경우 예외처리
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false) // url 정보만 return 하게될 것
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); // 클라이언트에 반환
    }
}
