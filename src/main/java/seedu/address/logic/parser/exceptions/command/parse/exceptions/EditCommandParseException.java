package seedu.address.logic.parser.exceptions.command.parse.exceptions;

import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents an edit command parse error encountered by an edit command parser.
 */
public class EditCommandParseException extends ParseException {
    /**
     * Creates a new ParseException with the specified invalid command format message and the cause of the error.
     */
    public EditCommandParseException() {
        super(MESSAGE_INVALID_INDEX);
    }
    /**
     * Creates a new ParseException with the specified invalid command format message.
     */
    public EditCommandParseException(String message) {
        super(message);
    }
}
