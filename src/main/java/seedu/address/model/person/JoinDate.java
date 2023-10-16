package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's join date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidJoinDate(String)}
 */
public class JoinDate {
    public static final String MESSAGE_CONSTRAINTS = "Join date should be in the form of DD/MM/YYYY. "
        + "Eg. 12/02/2023 represents 12th Feb 2023";

    public final String value;

    /**
     * Constructs a {@code JoinDate}.
     *
     * @param joinDate A valid join date in dd/mm/yyyy form.
     */
    public JoinDate(String joinDate) {
        requireNonNull(joinDate);
        checkArgument(isValidJoinDate(joinDate), MESSAGE_CONSTRAINTS);
        value = joinDate;
    }

    /**
     * Returns true if a given string is a valid join date in dd/mm/yyyy form.
     */
    public static boolean isValidJoinDate(String test) {
        try {
            DateTimeFormatter.ofPattern("d/M/yyyy").parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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
        if (!(other instanceof JoinDate)) {
            return false;
        }

        JoinDate otherJoinDate = (JoinDate) other;
        return value.equals(otherJoinDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
