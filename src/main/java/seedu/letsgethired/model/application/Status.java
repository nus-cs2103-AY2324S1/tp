package seedu.letsgethired.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.commons.util.AppUtil.checkArgument;

/**
 * Represents an InternApplication's status in the tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status implements Comparable<Status> {

    enum StatusEnum {
        REJECTED,
        ACCEPTED,
        PENDING,
        INTERVIEW,
        ASSESSMENT,
        OFFERED,
        ERROR;

        public static StatusEnum getEnum(String status) {
            String lowerKeyStatus = status.toLowerCase();
            switch (lowerKeyStatus) {
            case "rejected":
                return REJECTED;
            case "accepted":
                return ACCEPTED;
            case "pending":
                return PENDING;
            case "interview":
                return INTERVIEW;
            case "assessment":
                return ASSESSMENT;
            case "offered":
                return OFFERED;
            default:
                //Return an error enum to indicate that none of the Status matches the string
                return ERROR;
            }
        }
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Status can only be 'assessment', pending', 'accepted', 'interview', offered' or 'rejected'";

    public final String value;

    /**
     * Constructs an {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        value = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidStatus(String test) {
        StatusEnum status = StatusEnum.getEnum(test);
        return status != StatusEnum.ERROR;
    }

    @Override
    public String toString() {
        return value;
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
        return value.equals(otherStatus.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Status other) {
        return this.value.compareTo(other.value);
    }

}

