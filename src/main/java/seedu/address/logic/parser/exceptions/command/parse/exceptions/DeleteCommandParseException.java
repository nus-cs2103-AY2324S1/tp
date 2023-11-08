package seedu.address.logic.parser.exceptions.command.parse.exceptions;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a delete command parse error encountered by a delete command parser.
 */
public class DeleteCommandParseException extends ParseException {
    /**
     * Creates a new ParseException with the specified invalid command format message and the cause of the error.
     */
    public DeleteCommandParseException(DeleteCommandParseException de) {
        super(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), de);
    }
    /**
     * Creates a new ParseException with the specified invalid command format message.
     */
    public DeleteCommandParseException() {
        super("At least one index must be provided. " + DeleteCommand.MESSAGE_USAGE);
    }
}
