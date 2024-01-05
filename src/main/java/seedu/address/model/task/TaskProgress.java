package seedu.address.model.task;

/**
 * Enum representing the progress levels of tasks.
 */
public enum TaskProgress {

    /**
     * Progress levels
     */
    NOT_STARTED, PENDING, DONE;

    /**
     * A constant message indicating the constraints for valid task progress.
     */
    public static final String MESSAGE_CONSTRAINTS = "TaskProgress should only be not_started, pending, done";
}
