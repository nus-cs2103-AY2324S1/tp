package seedu.address.model.task;

/**
 * Enum representing the priority levels of tasks.
 */
public enum TaskPriority {

    /**
     * Priority levels
     */
    LOW, MEDIUM, HIGH;

    /**
     * A constant message indicating the constraints for valid task priorities.
     */
    public static final String MESSAGE_CONSTRAINTS = "TaskPriority should only be low, medium, or high";
}
