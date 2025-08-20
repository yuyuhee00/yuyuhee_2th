package com.example.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeToString {
    public String formattedDate(LocalDateTime cDate) {
        return cDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"));
    }
}