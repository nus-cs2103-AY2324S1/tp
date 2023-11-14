package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UncompletePlanCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UncompletePlanCommand object
 */
public class UncompletePlanCommandParser implements Parser<UncompletePlanCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UncompletePlanCommand
     * and returns a UncompletePlanCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UncompletePlanCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UncompletePlanCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UncompletePlanCommand.MESSAGE_USAGE), pe);
        }
    }

}
