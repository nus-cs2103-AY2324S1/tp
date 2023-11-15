package seedu.address.model.displayable;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.AppUtil;

/**
 * Represents a Displayable's priority level in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    public static final String MESSAGE_RECOMMENDATIONS = "Inputs should be 'high', 'medium', 'low' or 'nil'. However,"
            + "if at least the first letter is valid, we will read correctly.";

    public static final String MESSAGE_CONSTRAINTS =
            "Priority inputs must at least start with h for high, m for medium, l for low, or n for nil. "
                   + "This is not case sensitive. ";
    // Inputs are either 'high', 'medium', or 'low', with some allowance for typos after the first letter, and
    // are case-insensitive
    public static final String VALIDATION_REGEX = "(?i)^\\s*[hmln](.*)$";
    public static final String AFFIRMATION_REGEX = "(?i)(h[igh]{0,3}|m[edium]{0,5}|l[ow]{0,2}|n[il]{0,2})$";
    public static final String DEFAULT_PRIO_LVL = "NIL";
    public final PrioLvl value;

    /**
     * Represents the fixed priority levels tagged to clients in the address book.
     */
    public enum PrioLvl {
        HIGH,
        MEDIUM,
        LOW,
        NIL
    }

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority A valid priority.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        AppUtil.validateArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        this.value = getPrioLvl(priority);
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isAppropriatePriority(String test) {
        return test.matches(AFFIRMATION_REGEX);
    }

    /**
     * Returns a PrioLvl based on the user input.
     */
    public static PrioLvl getPrioLvl(String priority) {
        requireNonNull(priority);
        AppUtil.validateArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        char firstLetter = priority.charAt(0);
        if (firstLetter == 'h') {
            return PrioLvl.HIGH;
        } else if (firstLetter == 'm') {
            return PrioLvl.MEDIUM;
        } else if (firstLetter == 'l') {
            return PrioLvl.LOW;
        } else {
            return PrioLvl.NIL;
        }
    }

    @Override
    public String toString() {
        if (value == PrioLvl.HIGH) {
            return "high";
        } else if (value == PrioLvl.MEDIUM) {
            return "med";
        } else if (value == PrioLvl.LOW) {
            return "low";
        } else {
            return "nil";
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

    public boolean isPriorityNil() {
        return value == PrioLvl.NIL;
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
