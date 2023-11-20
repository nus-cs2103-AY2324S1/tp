package seedu.address.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Helper class for handling meeting time formatting and parsing.
 */
public class MeetingTimeFormatter {

    public static final String DATE_TIME_FORMAT = "dd/MM/uuuu HH:mm";
    private static DateTimeFormatter getFormatter() {
        return DateTimeFormatter
                .ofPattern(DATE_TIME_FORMAT)
                .withResolverStyle(ResolverStyle.STRICT);
    }

    public static LocalDateTime parse(String meetingTime) {
        return LocalDateTime.parse(meetingTime, getFormatter());
    }

    public static String format(LocalDateTime value) {
        return value.format(getFormatter());
    }

    /**
     * Returns true if a given string is a valid meeting time.
     */
    public static boolean isValid(String test) {
        try {
            parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
