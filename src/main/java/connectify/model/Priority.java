package connectify.model;

import static java.util.Objects.requireNonNull;

import connectify.commons.util.AppUtil;

/**
 * Represents a Priority in the Connectify.
 */
public abstract class Priority {

    public static final String VALIDATION_REGEX = "^[0-9]+$";
    public static final String MESSAGE_CONSTRAINTS =
            "Priority should only contain numbers, and it should be at least 1 digit long";

    public final Integer value;

    /**
     * Constructor for Priority
     * @param value a value for the priority
     */
    public Priority(String value) {
        requireNonNull(value);
        AppUtil.checkArgument(isValidPriority(value.toString()), MESSAGE_CONSTRAINTS);
        this.value = Integer.parseInt(value);
    }

    /**
     * Constructor for Priority
     * @param value a value for the priority
     */
    public Priority(Integer value) {
        requireNonNull(value);
        this.value = value;
    }

    /**
     * Returns the value of the priority
     * @return the value of the priority
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the priority as a string
     * @return the priority as a string
     */
    @Override
    public String toString() {
        return value.toString();
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

        Priority otherWebsite = (Priority) other;
        return value.equals(otherWebsite.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
