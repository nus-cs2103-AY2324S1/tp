package seedu.address.logic.parser.exceptions;

import static seedu.address.logic.Messages.MESSAGE_JSON_LIKE_INPUT_DETECTED;

/**
 * Represents a json injection parse error encountered by a json injection parser.
 */
public class JsonInjectionParseException extends ParseException {
    /**
     * Creates a new ParseException with the specified json like input detected message.
     */
    public JsonInjectionParseException() {
        super(MESSAGE_JSON_LIKE_INPUT_DETECTED);
    }
}
