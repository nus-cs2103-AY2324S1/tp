package seedu.address.model.schedule;

/**
 * Enum class representing the status of a schedule task.
 * This can be one of the following:
 * - {@code MISSED}: Indicates that the schedule was not completed.
 * - {@code COMPLETED}: Indicates that the schedule has been successfully completed.
 * - {@code PENDING}: Indicates that the schedule is yet to be marked.
 */
public enum Status {
    MISSED,
    COMPLETED,
    PENDING;

    public static final String MESSAGE_CONSTRAINTS = "Status has to be either MISSED, COMPLETED or PENDING";

    /**
     * Checks if the provided status string is a valid schedule status.
     *
     * @param status The status string to be validated.
     * @return {@code true} if the status is valid, {@code false} otherwise.
     */
    public static boolean isValidStatus(String status) {
        try {
            Status.valueOf(status.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
