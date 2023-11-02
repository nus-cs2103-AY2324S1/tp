package seedu.address.model.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

public class RandomIndexNotInitialisedException extends IllegalValueException {

    private static final String ERROR_MESSAGE = "Random index is not set yet.";

    public RandomIndexNotInitialisedException() {
        super(ERROR_MESSAGE);
    }
}
