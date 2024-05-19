package org.example.domain.holiday;

import org.example.util.HolidaysJsonParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.time.*;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductionCalendarTest {

    static ProductionCalendar calendar;

    @BeforeAll
    static void setUp() {
        List<Holidays> holidays = HolidaysJsonParser.parseHolidays(
                "src/main/resources/productCalendar.json");
        Holidays[] holidaysArr = holidays.toArray(new Holidays[0]);
        OffsetTime startWorkTime = OffsetTime.of(LocalTime.of(9, 0),
                ZoneOffset.ofHoursMinutes(3, 0));
        OffsetTime endWorkTime = OffsetTime.of(LocalTime.of(18, 0),
                ZoneOffset.ofHoursMinutes(3, 0));

        calendar = new ProductionCalendar(holidaysArr, startWorkTime, endWorkTime);
    }

    @ParameterizedTest
    @MethodSource("testLocalDate")
    void testCheckWeekend(LocalDate date, boolean expected) {
        boolean actual = calendar.checkHoliday(date);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("testOffsetDateTime")
    void testCheckWorkDay(OffsetDateTime dateTime, boolean expected) {
        boolean actual = calendar.checkWorkDays(dateTime);
        assertEquals(expected, actual);
    }

    //1-st lvl tests
    static Stream<Arguments> testLocalDate() throws IOException {
        List<LocalDateTestCase> testCases = JsonTestLoader.loadLocalDateTestCases("src/test/resources/testLocalDate.json");
        return testCases.stream().map(testCase ->
                Arguments.of(testCase.getDate(), testCase.isHoliday())
        );
    }

    //2-nd lvl tests
    static Stream<Arguments> testOffsetDateTime() throws IOException {
        List<OffsetDateTimeTestCase> testCases = JsonTestLoader.loadOffsetDateTimeTestCases("src/test/resources/testOffsetDateTime.json");
        return testCases.stream().map(testCase ->
                Arguments.of(testCase.getDateTime(), testCase.isHoliday())
        );
    }
}