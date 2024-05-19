package org.example.domain.holiday;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LocalDateTestCase {
    private LocalDate date;

    @JsonProperty("isHoliday")
    private boolean isHoliday;
}