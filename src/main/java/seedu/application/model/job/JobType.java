package seedu.application.model.job;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.util.AppUtil;

/**
 * Represents a Job's jobType in the application book.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobType(String)}
 */
public class JobType {

    public static final String MESSAGE_CONSTRAINTS =
            "Type is not case sensitive and should only be in the form: \n"
                    + "FULL_TIME, PART_TIME, INTERNSHIP, TEMPORARY, CONTRACT, FREELANCE, VOLUNTEER";

    public static final String TO_ADD_JOB_TYPE = "TO_ADD_JOB_TYPE";
    public static final JobType EMPTY_JOB_TYPE = new JobType(TO_ADD_JOB_TYPE);
    public final String jobType;
    /**
     * Enum for job types
     */
    public enum JobTypes {
        FULL_TIME, PART_TIME, INTERNSHIP, TEMPORARY, CONTRACT, FREELANCE, VOLUNTEER
    }

    /**
     * Constructs a {@code JobType}.
     *
     * @param jobType A valid jobType.
     */
    public JobType(String jobType) {
        requireNonNull(jobType);
        AppUtil.checkArgument(isValidJobType(jobType), MESSAGE_CONSTRAINTS);
        if (isEmptyJobType(jobType)) {
            this.jobType = TO_ADD_JOB_TYPE;
        } else {
            this.jobType = jobType;
        }
    }

    /**
     * Returns true if a given string is a valid jobType.
     */
    public static boolean isValidJobType(String test) {
        if (isEmptyJobType(test)) {
            return true;
        }
        for (JobType.JobTypes t : JobType.JobTypes.values()) {
            if (test.equalsIgnoreCase(t.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a given string is an empty jobType.
     */
    private static boolean isEmptyJobType(String test) {
        return test.equals(TO_ADD_JOB_TYPE);
    }

    @Override
    public String toString() {
        if (isEmptyJobType(jobType)) {
            return TO_ADD_JOB_TYPE;
        } else {
            return jobType;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof JobType)) {
            return false;
        }

        JobType otherJobType = (JobType) other;
        return jobType.equals(otherJobType.jobType);
    }

    @Override
    public int hashCode() {
        return jobType.hashCode();
    }
}
