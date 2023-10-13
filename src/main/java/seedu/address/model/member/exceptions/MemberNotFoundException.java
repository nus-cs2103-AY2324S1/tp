package seedu.address.model.member.exceptions;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
        super("Unable to find member in the address book");
    }
}
