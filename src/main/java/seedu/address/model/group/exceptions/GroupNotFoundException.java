package seedu.address.model.group.exceptions;

/**
 * Signals that the operation is looking for a group that does not exist.
 */

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() {
        super("Group not found.");
    }
}
