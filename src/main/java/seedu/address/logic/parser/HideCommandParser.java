package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.HideCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new HideCommand object.
 */
public class HideCommandParser implements Parser<HideCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HideCommand
     * and returns a HideCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public HideCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new HideCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HideCommand.MESSAGE_USAGE), pe);
        }
    }
}
