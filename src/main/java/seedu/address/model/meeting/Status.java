package seedu.address.model.meeting;

/**
 * Contains a Boolean representing whether a meeting is completed.
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS = "Status must be exactly 'true' or 'false'";
    private Boolean isComplete;

    /**
     * Constructs a {@code Status} field representing whether a meeting is complete.
     */
    public Status(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * Returns the current status
     */
    public Boolean get() {
        return isComplete;
    }

    /**
     * Changes the completion status to true
     *
     * @throws IllegalStateException if it is already completed
     */
    public void mark() {
        if(isComplete) { 
            throw new IllegalStateException();
        }

        isComplete = true;
    }

    @Override
    public int hashCode() {
        return isComplete.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Status)) {
            return false;
        }

        Status completed = (Status) other;
        return isComplete.equals(completed.isComplete);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return isComplete + " ";
    }
}
