package com.sugang.toys.command.common.util;

import java.time.LocalDateTime;

public class LocalDateTimeUtil {
    private LocalDateTimeUtil(){}

    public static boolean isAfter(LocalDateTime a1, LocalDateTime a2)
    {
        return a1.isAfter(a2);
    }
}
