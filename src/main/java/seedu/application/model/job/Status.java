package seedu.application.model.job;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.util.AppUtil;

/**
 * Represents a Job's status in the application book.
 */
public class Status {

    public static final String STATUS_SPECIFIER = "-s";
    public static final String MESSAGE_CONSTRAINTS =
            "Status is not case sensitive and should only be in the form: TO_BE_SUBMITTED, PENDING, APPROVED, REJECTED";

    public static final String IN_PROGRESS = JobStatus.TO_BE_SUBMITTED.toString();
    public static final Status DEFAULT_STATUS = new Status(IN_PROGRESS);

    public final String status;

    /**
     * The enumeration representing the status of a job application.
     * Possible values are:
     *   TO_BE_SUBMITTED: The job application is yet to be submitted.
     *   PENDING: The job application is pending review or processing.
     *   APPROVED: The job application has been approved.
     *   REJECTED: The job application has been rejected.
     */
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
