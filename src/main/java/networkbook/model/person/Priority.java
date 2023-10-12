package networkbook.model.person;

import static java.util.Objects.requireNonNull;
import static networkbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's priority level in the network book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(PRIORITY_LEVEL)}
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS =
            "Priority is among {high, medium, low}, either the word or the first letter, and is not case-sensitive";

    private final PRIORITY_LEVEL value;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priorityString A valid String representation of a priority level.
     */
    public Priority(String priorityString) {
        requireNonNull(priorityString);
        PRIORITY_LEVEL priorityLevel = parsePriorityLevel(priorityString);
        checkArgument(isValidPriority(priorityLevel), MESSAGE_CONSTRAINTS);
        value = priorityLevel;
    }

    /**
     * Parses user input {@code priorityString} into a {@code PRIORITY_LEVEL}
     *
     * @param priorityString provided in user command
     * @return corresponding PRIORITY_LEVEL
     */
    public static PRIORITY_LEVEL parsePriorityLevel(String priorityString) {
        priorityString = priorityString.toLowerCase();
        switch (priorityString){
        case "high":
        case "h":
            return Priority.PRIORITY_LEVEL.HIGH;
        case "medium":
        case "m":
            return Priority.PRIORITY_LEVEL.MEDIUM;
        case "low":
        case "l":
            return Priority.PRIORITY_LEVEL.LOW;
        default:
            return Priority.PRIORITY_LEVEL.INVALID;
        }
    }

    /**
     * Returns true if a given priority level is valid.
     */
    public static boolean isValidPriority(PRIORITY_LEVEL priorityLevel) {
        return priorityLevel != PRIORITY_LEVEL.INVALID;
    }

    @Override
    public String toString() {
        switch (value) {
        case HIGH:
            return "High";
        case MEDIUM:
            return "Medium";
        case LOW:
            return "LOW";
        default:
            return "Invalid priority level";
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

        Priority otherPhone = (Priority) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public enum PRIORITY_LEVEL {
        HIGH,
        MEDIUM,
        LOW,
        INVALID
    }

}
