package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an employee's Leave in the ManageHR.
 * Guarantees: immutable; is valid as declared in {@link #isValidLeave(String)}
 */
public class Leave {
    public static final String MESSAGE_CONSTRAINTS = "Leave should be in whole numbers";
    public static final String VALIDATION_REGEX = "\\d+";
    public final String value;

    /**
     * Constructs a {@code Leave}.
     *
     * @param leave A valid Leave.
     */
    public Leave(String leave) {
        requireNonNull(leave);
        checkArgument(isValidLeave(leave), MESSAGE_CONSTRAINTS);
        value = leave;
    }

    /**
     * Returns true if a given string is a valid Leave.
     */
    public static boolean isValidLeave(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof Leave)) {
            return false;
        }

        Leave otherLeave = (Leave) other;
        return value.equals(otherLeave.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
