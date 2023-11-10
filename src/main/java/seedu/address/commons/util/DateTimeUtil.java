package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helper functions for handling LocalDateTime objects.
 */
public class DateTimeUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm");
    public static final DateTimeFormatter VERBOSE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy, HHmm");

    /**
     * Returns a formatted string of the {@code dateTime} object.
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    /** */
    public static String verbose(LocalDateTime dateTime) {
        return dateTime.format(VERBOSE_FORMATTER);
    }

    /**
     * Returns a parsed {@code LocalDateTime} object of {@code string}.
     */
    public static LocalDateTime parse(String string) {
        return LocalDateTime.parse(string, FORMATTER);
    }
}
