package seedu.address.logic.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Checks if a date is valid or real
 */
public class DateValidator {
    private static final String DATE_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

    public static boolean isValidDate(String date) {
        return date.matches(DATE_REGEX) && isValidDateFormat(date) && isValidDayOfMonth(date);
    }

    private static boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isValidDayOfMonth(String date) {
        LocalDate localDate = LocalDate.parse(date);
        int dayOfMonth = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();

        switch (month) {
        case 2:
            // Check for leap year
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                return dayOfMonth <= 29;
            } else {
                return dayOfMonth <= 28;
            }
        case 4:
        case 6:
        case 9:
        case 11:
            return dayOfMonth <= 30;
        default:
            return dayOfMonth <= 31;
        }
    }
}
