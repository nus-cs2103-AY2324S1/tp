package seedu.address.model.fields;

/**
 * Represents a task name.
 */
public class Name extends NonEmptyText {
    public Name(String name) {
        super(name);
    }
}
