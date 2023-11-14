package seedu.address.model.fields;

import static java.util.Objects.requireNonNull;

/**
 * Represents the completion status
 * of a task.
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS = "complete or incomplete";
    private final Boolean isCompleted;

    /**
     * Constructs a {@code Name}.
     *
     * @param isCompleted A valid name.
     */
    public Status(Boolean isCompleted) {
        requireNonNull(isCompleted);
        this.isCompleted = isCompleted;
    }

    public static boolean isValidStatus(String date) {
        return date.equals("complete") || date.equals("incomplete");
    }

    public Boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public String toString() {
        return isCompleted ? "complete" : "incomplete";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Status)) {
            return false;
        }

        Status otherStatus = (Status) other;
        return isCompleted.equals(otherStatus.isCompleted);
    }

    @Override
    public int hashCode() {
        return isCompleted.hashCode();
    }
}
