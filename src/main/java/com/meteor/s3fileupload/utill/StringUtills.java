package com.meteor.s3fileupload.utill;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class StringUtills {

    public static LocalDateTime getLocalDateTime(String yyyyMMdd, String HHmm) {
        String str = yyyyMMdd + " "+HHmm;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(str, formatter);
    }

    public static String getCurrentDate() {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return current.format(formatter);
    }


}
