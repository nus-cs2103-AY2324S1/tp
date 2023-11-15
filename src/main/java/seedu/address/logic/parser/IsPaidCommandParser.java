package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.IsPaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new IsPaidCommand object
 * (Make use of the template of DeleteCommandParser and did some modifications)
 */
public class IsPaidCommandParser implements Parser<IsPaidCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the IsPaidCommand
     * and returns a IsPaidCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public IsPaidCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new IsPaidCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, IsPaidCommand.MESSAGE_USAGE), pe);
        }
    }

}
