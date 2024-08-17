package com.wskang.trip.exception;

/**
 * ResourceNotFoundException
 *
 * DB 검색 시 값이 없는 경우와 같이 Resource 를 찾을 수 없는 경우의 예외처리 정의
 *
 */

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
