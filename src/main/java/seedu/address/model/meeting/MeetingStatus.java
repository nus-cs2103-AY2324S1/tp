package seedu.address.model.meeting;

/**
 * Contains a Boolean representing whether a meeting is completed.
 */
public class MeetingStatus {

    public static final String MESSAGE_CONSTRAINTS = "Status must be exactly 'true' or 'false'";
    public final Boolean isComplete;

    /**
     * Constructs a {@code Status} field representing whether a meeting is complete.
     */
    public MeetingStatus(Boolean isComplete) {
        this.isComplete = isComplete;
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

        if (!(other instanceof MeetingStatus)) {
            return false;
        }

        MeetingStatus completed = (MeetingStatus) other;
        return isComplete.equals(completed.isComplete);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return isComplete.toString();
    }
}
