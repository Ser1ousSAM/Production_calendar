package org.example.domain.holiday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionCalendar {
    private Holidays[] holidays;
    private OffsetTime startWorkDay;
    private OffsetTime endWorkDay;
    private final int REDUCED_HOUR = 1;

    public boolean checkWorkDays(@NotNull OffsetDateTime dateTime) {
        boolean isHoliday = checkHoliday(dateTime.toLocalDate());
        if (isHoliday) {
            return true;
        } else {
            OffsetTime time = dateTime.toOffsetTime();
            if (time.isBefore(startWorkDay) || time.isAfter(endWorkDay)) {
                return true;
            } else if (checkReducedDay(dateTime.toLocalDate()) &&
                    (time.isBefore(startWorkDay) ||
                            time.isAfter(endWorkDay.minusHours(REDUCED_HOUR)))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkHoliday(@NotNull LocalDate date) {
        Holidays holidaysMonth = holidays[date.getMonthValue() - 1];
        int[] weekends = holidaysMonth.getWeekend();

        for (int weekend : weekends) {
            if (weekend == date.getDayOfMonth()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkReducedDay(@NotNull LocalDate date) {
        Holidays holidaysMonth = holidays[date.getMonthValue() - 1];
        int[] reducedDays = holidaysMonth.getReducedDay();

        for (int reducedDay : reducedDays) {
            if (reducedDay == date.getDayOfMonth()) {
                return true;
            }
        }
        return false;
    }

}
