package com.wskang.trip.exception;

import org.springframework.dao.DataIntegrityViolationException;
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
                "NOT_FOUND",
                webRequest.getDescription(false) // url 정보만 return 하게될 것
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); // 클라이언트에 반환
    }

    // 요청된 데이터에 대해 이미 중복된 값이 있어 서버에서 처리를 거부하는 경우 예외처리 (ex. 중복된 이름으로 회원가입 시도)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> handleBadRequestException(BadRequestException exception,
                                                               WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                exception.getErrorType(),
                webRequest.getDescription(false) // url 정보만 return 하게될 것
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); // 클라이언트에 반환
    }

    // 데이터베이스 제약 조건에 의한 예외처리 (ex. not null Column에 null이 들어오려는 경우)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(DataIntegrityViolationException exception,
                                                                        WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                "NULL_VIOLATION",
                webRequest.getDescription(false) // url 정보만 return 하게될 것
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); // 클라이언트에 반환
    }
}
