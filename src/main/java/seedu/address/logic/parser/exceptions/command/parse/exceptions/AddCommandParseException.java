package seedu.address.logic.parser.exceptions.command.parse.exceptions;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents an add command parse error encountered by an add command parser.
 */
public class AddCommandParseException extends ParseException {
    /**
     * Creates a new ParseException with the specified invalid command format message and the cause of the error.
     */
    public AddCommandParseException() {
        super(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
