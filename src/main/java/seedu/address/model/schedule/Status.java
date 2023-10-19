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
    PENDING,
}
