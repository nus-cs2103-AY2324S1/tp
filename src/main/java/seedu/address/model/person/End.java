package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's End in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEnd(String)}
 */

public class End {

    public static final String MESSAGE_CONSTRAINTS = "That is not a valid time format. End has a format of HHMM";
    public static final String VALIDATION_REGEX = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$";
    public final String value;

    /**
     * Constructs a {@code End}.
     *
     * @param end A valid phone number.
     */
    public End(String end) {
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
        if (!(other instanceof End)) {
            return false;
        }

        End otherEnd = (End) other;
        return value.equals(otherEnd.value);
    }
}
