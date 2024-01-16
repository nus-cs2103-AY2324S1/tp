package seedu.address.model.tag.exceptions;

/**
 * Represents an exception that is thrown when a specified tag is not found in the tag manager.
 */
public class TagNotFoundException extends RuntimeException {

    /**
     * Constructs a TagNotFoundException with a default error message.
     * The error message indicates that the tag was not found and suggests creating the tag before using it.
     */
    public TagNotFoundException() {
        super("Tag not found! Create tag before tagging a person with it!");
    }
}
