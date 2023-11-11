package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnPaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnPaidCommand object
 * (Make use of the template of DeleteCommandParser and did some modifications)
 */
public class UnPaidCommandParser implements Parser<UnPaidCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnPaidCommand
     * and returns a UnPaidCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnPaidCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnPaidCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnPaidCommand.MESSAGE_USAGE), pe);
        }
    }
}
