package networkbook.model.person;

import static java.util.Objects.requireNonNull;
import static networkbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's priority level in the network book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(PriorityLevel)}
 */
public class Priority {

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
        return priorityLevel != PriorityLevel.INVALID;
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

    /**
     * Enumerates the possible priority levels of a contact.
     */
    public enum PriorityLevel {
        HIGH,
        MEDIUM,
        LOW,
        INVALID
    }

}
