package com.mob7.vehiclestaytime.infrastructure.gateways.impl.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDateTime getLocalDateTime(String date, boolean isStartDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate ld;
        LocalDateTime dateLdt = null;
        if (date != null) {
            ld = LocalDate.parse(date, dateTimeFormatter);
            if (isStartDate) {
                dateLdt = LocalDateTime.of(ld, LocalDateTime.MIN.toLocalTime());
            } else {
                dateLdt = LocalDateTime.of(ld, LocalDateTime.MAX.toLocalTime());
            }
        }
        return dateLdt;
    }
}
