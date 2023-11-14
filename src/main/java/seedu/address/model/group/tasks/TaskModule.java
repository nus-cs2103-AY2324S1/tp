package seedu.address.model.group.tasks;

/**
 * The `TaskModule` enum represents the module for a task, which can be either CS2103T or CS2101.
 * It provides symbolic representations for the task module.
 */
public enum TaskModule {
    /**
     * Represents the CS2103T module.
     */
    CS2103T,

    /**
     * Represents the CS2101 module.
     */
    CS2101;

    public static final String MESSAGE_CONSTRAINTS = "Module must either be CS2103T or CS2101";

    /**
     * Returns the string representation of the task module.
     *
     * @return A string representation of the task module.
     */
    @Override
    public String toString() {
        return name(); // This returns the name of the enum constant (CS2103T or CS2101).
    }
    /**
     * Checks if the given string is a valid task module.
     *
     * @param test The string to test.
     * @return True if the string is a valid task module, false otherwise.
     */
    public static boolean isValidModule(String test) {
        for (TaskModule module : TaskModule.values()) {
            if (module.toString().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
