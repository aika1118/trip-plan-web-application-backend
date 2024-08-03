package com.wskang.trip.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateTimeUtil {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    // 주어진 문자열이 HH:mm 형식의 유효한 시간인지 검사
    public static boolean isValidTime(String timeStr) {
        if (timeStr == null || timeStr.isEmpty())
            return false;

        try {
            LocalTime.parse(timeStr, TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
