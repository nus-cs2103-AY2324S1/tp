package seedu.address.logic.parser.exceptions.command.parse.exceptions;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a find command parse error encountered by a find command parser.
 */
public class FindCommandParseException extends ParseException {
    /**
     * Creates a new ParseException with the specified invalid command format message and the cause of the error.
     */
    public FindCommandParseException() {
        super(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
