package group.g22.demostore.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConversionHelper {
    public static LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
