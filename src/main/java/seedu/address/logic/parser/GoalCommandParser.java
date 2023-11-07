package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.GoalCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class GoalCommandParser implements Parser<GoalCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GoalCommand
     * and returns a GoalCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GoalCommand parse(String args) throws ParseException {
        assert args != null : "Command is empty";

        try {
            int index = ParserUtil.parseTarget(args);
            return new GoalCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoalCommand.MESSAGE_USAGE), pe);
        }
    }

}
