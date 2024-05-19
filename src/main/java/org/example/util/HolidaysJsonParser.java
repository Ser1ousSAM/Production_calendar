package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.holiday.Holidays;
import org.example.domain.inputJson.HolidaysJson;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class HolidaysJsonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static HolidaysJson[] fetchHoliday(String path) {
        HolidaysJson[] holiday = null;
        try {
            holiday = objectMapper.readValue(new File(path), HolidaysJson[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return holiday;
    }

    public static List<Holidays> parseHolidays(String path) {

        //holidaysJsons contains holidays + reduced working days
        HolidaysJson[] holidaysJsons = fetchHoliday(path);

        List<Holidays> result = new LinkedList<>();

        for (HolidaysJson monthHolidays : holidaysJsons) {
            String[] days = monthHolidays.getDays().split("[,\s]+");
            List<Integer> holidays = new LinkedList<>();
            List<Integer> reducedDays = new LinkedList<>();
            for (String day : days) {
                if (day.contains("*")) {
                    day = day.replace("*", "");
                    reducedDays.add(Integer.valueOf(day));
                } else {
                    holidays.add(Integer.valueOf(day));
                }
            }
            result.add(new Holidays(
                    monthHolidays.getMonth(),
                    holidays.stream().mapToInt(x -> x.intValue()).toArray(),
                    reducedDays.stream().mapToInt(x -> x.intValue()).toArray()));
        }
        return result;
    }
}
