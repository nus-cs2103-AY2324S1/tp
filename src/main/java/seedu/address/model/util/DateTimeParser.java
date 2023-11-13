package seedu.address.model.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for formatting time and date strings.
 */
public class DateTimeParser {
    /**
     * The input time format, expected to be in "HH:mm" format.
     */
    public static final DateTimeFormatter INPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * The input date format, expected to be in "yyyy-MM-dd" format.
     */
    public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * The output time format, formatted as "h.mma".
     */
    public static final DateTimeFormatter OUTPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("h.mma");

    /**
     * The output date format, formatted as "dd MMMM yyyy".
     */
    public static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    /**
     * Formats the given time string to a more readable format.
     *
     * @param time The time string in "HH:mm" format to be formatted.
     * @return A formatted time string in "h.mma" format (e.g., 3.30PM).
     */
    public static String formatTime(String time) {
        LocalTime localTime = LocalTime.parse(time, INPUT_TIME_FORMATTER);
        return localTime.format(OUTPUT_TIME_FORMATTER);
    }

    /**
     * Formats the given date string to a more readable format.
     *
     * @param date The date string in "yyyy-MM-dd" format to be formatted.
     * @return A formatted date string in "dd MMMM yyyy" format (e.g., 31 December 2023).
     */
    public static String formatDate(String date) {
        LocalDate localDate = LocalDate.parse(date, INPUT_DATE_FORMATTER);
        return localDate.format(OUTPUT_DATE_FORMATTER);
    }
}
