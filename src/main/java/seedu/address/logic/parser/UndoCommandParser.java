package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code UndoCommand} object.
 */
public class UndoCommandParser implements Parser<UndoCommand> {
    /**
     * Parses the given {@code String} of arguments to create an {@code UndoCommand} object.
     *
     * @param args The user input arguments.
     * @return A new {@code UndoCommand} object.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public UndoCommand parse(String args) throws ParseException {
        if (!args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
        }
        return new UndoCommand();
    }
}
