package seedu.address.model.tutorial.exceptions;
// @@author Propene-Dan
/**
 * Signals that duplicate Person is found in the tutorial
 * (Persons are considered duplicates if they have the same identity).
 */
public class StudentExistException extends RuntimeException {
    public StudentExistException() {
        super("Student already in the tutorial!");
    }
}
