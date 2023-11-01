package seedu.address.model.person.exceptions;

public class EmptyTasklistException extends RuntimeException {
    public EmptyTasklistException(){super("List of tasks is empty!");}
}
