package com.autotest.demobookingcom.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    
    public static String getCurrentTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
    }

    public static String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
    
}