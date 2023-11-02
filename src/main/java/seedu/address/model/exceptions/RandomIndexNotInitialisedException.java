package seedu.address.model.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * This exception should be thrown whenever there is a need to access the stored random index, but it is not initialised
 * for whatever reason. For example, running commands that use the random index without running the "random" command
 * first.
 */
public class RandomIndexNotInitialisedException extends IllegalValueException {

    private static final String ERROR_MESSAGE = "Random index is not set yet.";

    public RandomIndexNotInitialisedException() {
        super(ERROR_MESSAGE);
    }
}
