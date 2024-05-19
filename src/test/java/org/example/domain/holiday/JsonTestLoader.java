package org.example.domain.holiday;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonTestLoader {
    public static List<LocalDateTestCase> loadLocalDateTestCases(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(new File(filePath), new TypeReference<List<LocalDateTestCase>>() {});
    }

    public static List<OffsetDateTimeTestCase> loadOffsetDateTimeTestCases(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(new File(filePath), new TypeReference<List<OffsetDateTimeTestCase>>() {});
    }
}
