package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.HintCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HintCommand object
 */
public class HintCommandParser implements Parser<HintCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HintCommand parse(String args) throws ParseException {
        assert args != null : "Command is empty";
        String argsToCheck = args.trim();
        

        try {
            Index index = ParserUtil.parseIndex(args);
            return new HintCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HintCommand.MESSAGE_USAGE), pe);
        }
    }
}
