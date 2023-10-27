package seedu.address.model.interval;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Interval's End in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEnd(String)}
 */

public class IntervalEnd {

    public static final String MESSAGE_CONSTRAINTS = "That is not a valid time format. End has a format of HHMM";
    public static final String VALIDATION_REGEX = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$";
    public final String value;

    /**
     * Constructs a {@code End}.
     *
     * @param end A valid phone number.
     */
    public IntervalEnd(String end) {
        requireNonNull(end);
        checkArgument(isValidEnd(end), MESSAGE_CONSTRAINTS);
        value = end;
    }

    public static boolean isValidEnd(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private LocalTime parse(String test) {
        assert isValidEnd(test);

        String pattern = "HHmm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return LocalTime.parse(test, formatter);
    }

    public LocalTime getTime() {
        return parse(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.person.End)) {
            return false;
        }

        seedu.address.model.person.End otherEnd = (seedu.address.model.person.End) other;
        return value.equals(otherEnd.value);
    }
}
