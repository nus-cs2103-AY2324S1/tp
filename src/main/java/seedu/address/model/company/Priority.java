package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company's priority in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {
    public static final String MESSAGE_CONSTRAINTS_NON_EMPTY =
            "Oops! Priority description should not be blank! Please either try again with a valid priority, "
                    + "or remove the priority prefix. ";
    public static final String MESSAGE_CONSTRAINTS_VALID_REGEX =
            "Oops! You have entered an invalid priority! Priority should only be high, medium, "
                    + "low or none (to remove priority). \n"
                    + "Please try again.";


    public static final String VALIDATION_REGEX = "(HIGH|MEDIUM|LOW|NONE)";

    public final String priority;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority A valid priority.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(!priority.isBlank(), MESSAGE_CONSTRAINTS_NON_EMPTY);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS_VALID_REGEX);
        this.priority = priority;
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return priority;
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
        return priority.equals(otherPriority.priority);
    }

    @Override
    public int hashCode() {
        return priority.hashCode();
    }
}
