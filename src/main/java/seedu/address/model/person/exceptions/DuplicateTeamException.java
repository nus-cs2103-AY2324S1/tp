package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Teams (Teams are considered duplicates if they have the same
 * team name).
 */
public class DuplicateTeamException extends RuntimeException{
    public DuplicateTeamException() {
        super("Operation would result in duplicate persons");
    }
}
