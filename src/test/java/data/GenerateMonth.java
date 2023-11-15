package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GenerateMonth {
    String generateMonth(int month, String pattern) {
        return String.format(LocalDate.now().format(DateTimeFormatter.ofPattern(pattern)));
    }
}
