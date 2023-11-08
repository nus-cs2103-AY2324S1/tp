package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;

/**
 * Helper functions for handling datetimes.
 */
public class DateUtil {
    /**
     * Formats date and time inputs as: yyyy/MM/dd hh:mm (eg. 2020/02/20 08:00).
     */
    private static final DateTimeFormatter FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd HH:mm")
            .parseDefaulting(ChronoField.ERA, 1)
            .toFormatter()
            .withChronology(IsoChronology.INSTANCE)
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Parses a {@code String dateTime} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static LocalDateTime parseDateTime(String dateAndTime) {
        requireNonNull(dateAndTime);
        String trimmedDateTime = dateAndTime.trim();
        LocalDateTime localDateTime;
        localDateTime = LocalDateTime.parse(trimmedDateTime, FORMAT);
        return localDateTime;
    }

    /**
     * Returns a formatted {@code String dateTime} from a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String dateTimeToString(LocalDateTime dateAndTime) {
        requireNonNull(dateAndTime);
        return dateAndTime.format(FORMAT);
    }
}
