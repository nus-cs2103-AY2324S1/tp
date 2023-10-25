package seedu.address.model.team.exceptions;

/**
 * The type Developer not found exception.
 */
public class DeveloperNotFoundException extends RuntimeException {
    public DeveloperNotFoundException() {
        super("The specified developer could not be found in the team");
    }
}
