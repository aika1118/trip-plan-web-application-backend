package com.wskang.trip.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// DB 검색 시 값이 없는 경우 예외처리
@Getter
public class ResourceNotFoundException extends RuntimeException{

    //private final String errorType;

    public ResourceNotFoundException(String message) {
        super(message);
        //this.errorType = errorType;
    }
}
