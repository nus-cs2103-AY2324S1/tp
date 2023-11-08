package seedu.address.logic.parser.exceptions.command.parse.exceptions;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a help command parse error.
 */
public class HelpCommandParseException extends ParseException {
    /**
     * Creates a new ParseException with the specified invalid command format message.
     */
    public HelpCommandParseException() {
        super(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }
}
