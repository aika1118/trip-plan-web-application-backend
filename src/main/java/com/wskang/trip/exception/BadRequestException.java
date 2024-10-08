package com.wskang.trip.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * BadRequestException
 *
 * Bad Request 관련된 Exception 정의
 *
 */


@Getter
@AllArgsConstructor
public class BadRequestException extends RuntimeException{ // GlobalExceptionHandler 에 등록 후 사용
    private HttpStatus status;
    private String message;
    private String errorType;
}