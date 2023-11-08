package seedu.address.logic.parser.exceptions.command.parse.exceptions;

import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a delete command parse error encountered by a delete command parser.
 */
public class DeleteCommandParseException extends ParseException {
    /**
     * Creates a new ParseException with the specified invalid command format message and the cause of the error.
     */
    public DeleteCommandParseException() {
        super(MESSAGE_INVALID_INDEX);
    }
    /**
     * Creates a new ParseException with the specified invalid command format message.
     */
    public DeleteCommandParseException(String message) {
        super(message);
    }
}
