package com.example.coffeebe.domain.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Helper {

    public static LocalDateTime getTodayDateTimeHCMZone() {
        return LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
    }

}
