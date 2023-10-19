package networkbook.model.person;

import static java.util.Objects.requireNonNull;
import static networkbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's priority level in the network book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(PriorityLevel)}
 */
public class Priority implements Comparable<Priority> {

    public static final String MESSAGE_CONSTRAINTS =
            "Priority is among {high, medium, low}, either the word or the first letter, and is not case-sensitive";

    private final PriorityLevel value;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priorityString A valid String representation of a priority level.
     */
    public Priority(String priorityString) {
        requireNonNull(priorityString);
        PriorityLevel priorityLevel = parsePriorityLevel(priorityString);
        checkArgument(isValidPriority(priorityLevel), MESSAGE_CONSTRAINTS);
        value = priorityLevel;
    }

    /**
     * Parses user input {@code priorityString} into a {@code PriorityLevel}
     *
     * @param priorityString provided in user command
     * @return corresponding PriorityLevel
     */
    public static PriorityLevel parsePriorityLevel(String priorityString) {
        priorityString = priorityString.toLowerCase();
        switch (priorityString) {
        case "high":
        case "h":
            return Priority.PriorityLevel.HIGH;
        case "medium":
        case "m":
            return Priority.PriorityLevel.MEDIUM;
        case "low":
        case "l":
            return Priority.PriorityLevel.LOW;
        default:
            return Priority.PriorityLevel.INVALID;
        }
    }

    /**
     * Returns true if a given priority level is valid.
     */
    public static boolean isValidPriority(PriorityLevel priorityLevel) {
        requireNonNull(priorityLevel);
        return priorityLevel != PriorityLevel.INVALID;
    }

    @Override
    public String toString() {
        assert isValidPriority(value) : "Valid priority level should fall within the three categories";
        switch (value) {
        case HIGH:
            return "High";
        case MEDIUM:
            return "Medium";
        default:
            return "Low";
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

    /**
     * Enumerates the possible priority levels of a contact.
     */
    public enum PriorityLevel {
        HIGH(3),
        MEDIUM(2),
        LOW(1),
        INVALID(0);

        public final int value;
        private PriorityLevel(int value) {
            this.value = value;
        }
    }

    @Override
    public int compareTo(Priority o) {
        if (value.value > o.value.value) {
            return 1;
        } else if (value.value < o.value.value) {
            return -1;
        } else {
            return 0;
        }
    }

}
