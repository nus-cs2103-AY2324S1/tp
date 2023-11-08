package seedu.address.logic.parser.exceptions.command.parse.exceptions;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents an edit command parse error encountered by an edit command parser.
 */
public class EditCommandParseException extends ParseException {
    /**
     * Creates a new ParseException with the specified invalid command format message and the cause of the error.
     */
    public EditCommandParseException(EditCommandParseException ee) {
        super(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), ee);
    }
    /**
     * Creates a new ParseException with the specified invalid command format message.
     */
    public EditCommandParseException() {
        super(EditCommand.MESSAGE_NOT_EDITED);
    }
}
