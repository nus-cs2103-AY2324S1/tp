package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code RedoCommand} object.
 *
 * This parser ensures that the redo command provided by the user is valid.
 * If additional arguments are provided with the redo command, a ParseException is thrown.
 */
public class RedoCommandParser implements Parser<RedoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RedoCommand.
     *
     * @param args Input arguments provided by the user.
     * @return A RedoCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public RedoCommand parse(String args) throws ParseException {
        if (!args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RedoCommand.MESSAGE_USAGE));
        }
        return new RedoCommand();

    }

}
