package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ModeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments to produce an {@code ModeCommand}.
 */
public class ModeCommandParser implements Parser<ModeCommand> {

    /**
     * Parses the given {@code String} of arguments to produce an {@code UpcomingCommand}.
     * <p>
     * This parser expects no arguments. If any argument is provided, a {@code ParseException} is thrown.
     *
     * @param args The arguments string, which should be empty for this command.
     * @return An {@code UpcomingCommand} ready for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ModeCommand parse(String args) throws ParseException {
        if (!args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModeCommand.MESSAGE_USAGE));
        }
        return new ModeCommand();
    }
}
