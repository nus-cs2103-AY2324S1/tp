package seedu.address.model.fields;

import java.util.Optional;

/**
 * Represents the possible outcome for an application/task
 */
public enum Outcome {
    FOLLOW_UP, GHOSTED, REJECTED, OFFERED, ACCEPTED, AWAITING;

    @Override
    public String toString() {
        switch (this) {
        case FOLLOW_UP:
            return "Follow-up";
        case GHOSTED:
            return "Ghosted";
        case REJECTED:
            return "Rejected";
        case OFFERED:
            return "Offered";
        case ACCEPTED:
            return "Accepted";
        default:
            return "Awaiting";
        }
    }

    /**
     * Parse a string to an Outcome Enum
     * @param s to be parsed
     * @return an optional outcome
     */
    public static Optional<Outcome> parseOutcome(String s) {
        switch (s) {
        case "follow-up":
            return Optional.of(FOLLOW_UP);
        case "ghosted":
            return Optional.of(GHOSTED);
        case "rejected":
            return Optional.of(REJECTED);
        case "offered":
            return Optional.of(OFFERED);
        case "accepted":
            return Optional.of(ACCEPTED);
        case "awaiting":
            return Optional.of(AWAITING);
        default:
            return Optional.empty();
        }
    }
}
