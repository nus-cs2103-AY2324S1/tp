package seedu.application.model.job;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.util.AppUtil;

/**
 * Represents a Job's status in the application book.
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS =
            "Status is not case sensitive and should only be in the form: TO_BE_SUBMITTED, PENDING, APPROVED, REJECTED";

    public static final Status DEFAULT_STATUS = new Status(JobStatus.TO_BE_SUBMITTED.toString());

    public final String status;

    public enum JobStatus {
        TO_BE_SUBMITTED, PENDING, APPROVED, REJECTED
    }

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        AppUtil.checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.status = status.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidStatus(String test) {
        for (JobStatus s : JobStatus.values()) {
            if (test.equalsIgnoreCase(s.toString())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return status;
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
        return status.equals(otherStatus.status);
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

}
