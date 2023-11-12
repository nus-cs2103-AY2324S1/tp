package seedu.address.logic.parser.exceptions;

import java.util.Objects;

import seedu.address.commons.exceptions.IllegalValueException;



/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ParseException)) {
            return false;
        }

        ParseException otherParseException = (ParseException) other;
        return getMessage().equals(otherParseException.getMessage())
                && Objects.equals(getCause(), otherParseException.getCause());
    }
}
