package seedu.address.model.project.exceptions;

/**
 * Exception thrown when a project is not found in a specific operation.
 */
public class ProjectNotFoundException extends RuntimeException {

    /**
     * Constructs a ProjectNotFoundException with a default error message.
     */
    public ProjectNotFoundException() {
        super("Unable to find project in operation");
    }
}
