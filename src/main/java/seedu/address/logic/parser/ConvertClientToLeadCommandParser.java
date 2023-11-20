package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ConvertClientToLeadCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ConvertClientToLeadCommand object
 */
public class ConvertClientToLeadCommandParser implements Parser<ConvertClientToLeadCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ConvertClientToLeadCommand
     * and returns a ConvertClientToLeadCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ConvertClientToLeadCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertClientToLeadCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(args);
        return new ConvertClientToLeadCommand(index);
    }

}
