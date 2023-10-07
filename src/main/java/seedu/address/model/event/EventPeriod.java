package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class EventPeriod {
    public static final String MESSAGE_CONSTRAINTS = "The start date and time and end date and time should "
            + "be in the format 'yyyy-MM-dd HH:mm' where:\n"
            + "    -'yyyy' is the year.\n"
            + "    -'MM' is the month.\n"
            + "    -'dd' is the day.\n"
            + "    -'HH:mm' is the time in 24-hour format.";
    private static final DateTimeFormatter DATE_TIME_STRING_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm");

    private final LocalDateTime start;
    private final LocalDateTime end;

    public EventPeriod(String startString, String endString) {
        this(LocalDateTime.parse(startString, DATE_TIME_STRING_FORMATTER),
                LocalDateTime.parse(endString, DATE_TIME_STRING_FORMATTER));
    }

    private EventPeriod(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public static EventPeriod createNonConflictingPeriod() {
        return new EventPeriod(LocalDateTime.MIN, LocalDateTime.MIN);
    }

    public static boolean isValidPeriod(String startString, String endString) {
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        try {
            startDateTime = LocalDateTime.parse(startString, DATE_TIME_STRING_FORMATTER);
            endDateTime = LocalDateTime.parse(endString, DATE_TIME_STRING_FORMATTER);
        } catch (DateTimeParseException invalidFormatException) {
            return false;
        }
        return startDateTime.isBefore(endDateTime);
    }

    public boolean isOverlapping(EventPeriod other) {
        requireNonNull(other);

        return this.start.isBefore(other.end) && other.start.isBefore(this.end);
    }

    public int compareTo(EventPeriod other) {
        if (this.start.isBefore(other.start)) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "start: "
                + this.start.format(DATE_TIME_STRING_FORMATTER)
                + "; end: "
                + this.end.format(DATE_TIME_STRING_FORMATTER);
    }
}
