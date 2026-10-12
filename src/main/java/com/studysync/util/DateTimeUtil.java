package com.studysync.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static LocalDateTime convertToLocalDateTime(LocalDate date) {
        return date == null ? null : date.atStartOfDay();
    }
}
