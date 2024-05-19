package org.example.domain.holiday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Holidays {
    private int month;
    private int[] weekend;
    private int[] reducedDay;
}
