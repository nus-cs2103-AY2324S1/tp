package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.StatsCommand;
import seedu.address.logic.commands.StatsAvailCommand;
import seedu.address.logic.commands.StatsCurrentCommand;
import seedu.address.logic.commands.StatsHousingCommand;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new StatsCommand object
 */
public class StatsCommandParser implements CommandParser<StatsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StatsCommand
     * and returns either a StatsAvailCommand, StatsCurrentCommand
     * or StatsHousingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatsCommand parse(String args) throws ParseException {
        String field = args.trim();
        switch (field) {
        case StatsAvailCommand.COMMAND_WORD:
            return new StatsAvailCommand();
        case StatsCurrentCommand.COMMAND_WORD:
            return new StatsCurrentCommand();
        case StatsHousingCommand.COMMAND_WORD:
            return new StatsHousingCommand();
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
        }
    }
}
