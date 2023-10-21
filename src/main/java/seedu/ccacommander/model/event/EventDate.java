package seedu.ccacommander.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.ccacommander.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event's date in CcaCommander.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class EventDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format of YYYY-MM-DD, and it should not be blank";

    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("YYYY-MM-DD");
    public final LocalDate date;

    /**
     * Constructs a {@code Name}.
     *
     * @param dateString A valid date in String.
     */
    public EventDate(String dateString) {
        requireNonNull(dateString);
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(dateString);
    }

    /**
     * Returns true if a given date is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventDate)) {
            return false;
        }

        EventDate otherDate = (EventDate) other;
        return date.equals(otherDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
