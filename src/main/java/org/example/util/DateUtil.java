package org.example.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    // 輸入："2024-09-02T07:07:20+00:00"
    // 輸出："2024/09/02 07:07:20"
    public static String formatFromISO(String isoString) {
        LocalDateTime dateTime = OffsetDateTime.parse(isoString).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dateTime.format(formatter);
    }

}
