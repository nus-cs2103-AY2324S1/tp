package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;

public class DateUtil {
    /**
     * Formats date and time inputs as: yyyy-MM-dd hh:mm (eg. 2020-02-20 08:00).
     */
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    /**
     * Parses a {@code String dateTime} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static LocalDateTime parseDateTime(String dateAndTime) {
        requireNonNull(dateAndTime);
        String trimmedDateTime = dateAndTime.trim();
        LocalDateTime localDateTime;
        localDateTime = LocalDateTime.parse(trimmedDateTime, dateTimeFormat);
        return localDateTime;
    }

    /**
     * Returns a formatted {@code String dateTime} from a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String dateTimeToString(LocalDateTime dateAndTime) {
        requireNonNull(dateAndTime);
        return dateAndTime.format(dateTimeFormat);
    }
}
