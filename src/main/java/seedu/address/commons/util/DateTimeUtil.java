package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utilities about date and time
 */
public class DateTimeUtil {
    /**
     * Parse string into LocalDateTime
     * @param str the string to be parsed
     * @return The parsed LocalDateTime
     * @throws DateTimeParseException If the string cannot be parsed into LocalDateTime
     */
    public static LocalDateTime parseString(String str) throws DateTimeParseException {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm[:ss]");
        LocalDateTime result = null;
        try {
            result = LocalDateTime.parse(str, formatter1);
        } catch (DateTimeParseException e2) {
            LocalDateTime now = LocalDateTime.now();
            String appendDate = now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth() + " ";
            result = LocalDateTime.parse(appendDate + str, formatter1);
        }
        return result;
    }

    /**
     * Get the formatted String representation of the {@code LocalDateTime} object
     *
     * @param time The {@code LocalDateTime} object to be parsed
     * @return The String representation of this {@code LocalDateTime} object, in the format {@code yyyy-MM-dd HH:mm:ss}
     */
    public static String toFormattedString(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
