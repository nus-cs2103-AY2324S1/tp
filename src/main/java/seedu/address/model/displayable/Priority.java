package seedu.address.model.displayable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Displayable's priority level in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS =
            "Priority inputs are either 'high', 'medium', or 'low', and shouldn't be empty.";
    // Inputs are either 'high', 'medium', or 'low', with allowance for typos after the first letter
    public static final String VALIDATION_REGEX = "^(h(igh)?|m(edium)?|l(ow)?)$";

    public final PrioLvl value;

    public enum PrioLvl {
        HIGH,
        MEDIUM,
        LOW
    }

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority A valid priority.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        value = getPrioLvl(priority);
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a PrioLvl based on the user input
     */
    public static PrioLvl getPrioLvl(String priority) {
        if (priority == null) {
            return null;
        }
        char firstLetter = priority.charAt(0);
        if (firstLetter == 'h') {
            return PrioLvl.HIGH;
        } else if (firstLetter == 'm') {
            return PrioLvl.MEDIUM;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        if (value == PrioLvl.HIGH) {
            return "high";
        } else if (value == PrioLvl.MEDIUM) {
            return "medium";
        } else if (value == PrioLvl.LOW) {
            return "low";
        } else {
            return null;
        }
    }

    public String getBackgroundColor() {
        if (value == PrioLvl.HIGH) {
            return "red";
        } else if (value == PrioLvl.MEDIUM) {
            return "orange";
        } else if (value == PrioLvl.LOW) {
            return "green";
        } else {
            return "purple";
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Priority)) {
            return false;
        }

        Priority otherPriority = (Priority) other;
        return value.equals(otherPriority.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
