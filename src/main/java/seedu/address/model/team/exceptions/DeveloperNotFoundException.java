package seedu.address.model.team.exceptions;

public class DeveloperNotFoundException extends RuntimeException {
    public DeveloperNotFoundException() {
        super("The specified developer could not be found in the team");
    }
}
