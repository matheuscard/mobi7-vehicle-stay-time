package com.mob7.vehiclestaytime.infrastructure.gateways.impl.utils;

import com.mob7.vehiclestaytime.domain.model.Position;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class DateUtils {
    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
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

    /**
     *
     * @param plates
     * @return stayTimes of vehicle on interest point.
     */
    public static String getStayTime(final Map.Entry<String, List<Position>> plates) {
        LocalDateTime fromDateTime = plates.getValue().get(0).date();
        LocalDateTime toDateTime = plates.getValue().get(plates.getValue().size()-1).date();
        Period period = getPeriod(fromDateTime, toDateTime);
        long time[] = getTime(fromDateTime, toDateTime);
        return String.format("%d dias, %d horas, %d minutos e %d segundos",period.getDays(), time[0], time[1], time[2]);
    }



    private static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
        return Period.between(dob.toLocalDate(), now.toLocalDate());
    }
    private static long[] getTime(LocalDateTime dob, LocalDateTime now) {
        LocalDateTime today = LocalDateTime.of(now.getYear(),
                now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        Duration duration = Duration.between(today, now);

        long seconds = duration.getSeconds();

        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);

        return new long[]{hours, minutes, secs};
    }
}
