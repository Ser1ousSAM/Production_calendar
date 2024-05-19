package org.example.domain.holiday;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class OffsetDateTimeTestCase {
    private OffsetDateTime dateTime;

    @JsonProperty("isHoliday")
    private boolean isHoliday;
}