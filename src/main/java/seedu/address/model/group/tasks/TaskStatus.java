package seedu.address.model.group.tasks;

/**
 * The `TaskStatus` enum represents the status of a task, which can be either done or not done.
 * It provides symbolic representations for task status and allows converting status to a string.
 */
public enum TaskStatus {
    /**
     * Represents a task that is marked as done.
     */
    DONE("DONE"),

    /**
     * Represents a task that is not marked as done.
     */
    NOT_DONE("NOT_DONE");


    public static final String MESSAGE_CONSTRAINTS = "Status must either be DONE or NOT_DONE";
    private final String symbol;

    /**
     * Constructs a `TaskStatus` enum value with the specified symbolic representation.
     *
     * @param symbol The symbolic representation of the task status.
     */
    TaskStatus(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbolic representation of the task status.
     *
     * @return A string representation of the task status.
     */

    @Override
    public String toString() {
        return symbol;
    }

    /**
     * Checks if the given string is a valid task status.
     *
     * @param test The string to test.
     * @return True if the string is a valid task status, false otherwise.
     */
    public static boolean isValidStatus(String test) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.toString().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public String getSymbol() {
        if (this == DONE) {
            return "✅";
        } else {
            return "❌";
        }
    }
}
