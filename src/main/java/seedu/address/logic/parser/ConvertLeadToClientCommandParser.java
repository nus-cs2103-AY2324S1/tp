
package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ConvertLeadToClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ConvertLeadToClientCommand object
 */
public class ConvertLeadToClientCommandParser implements Parser<ConvertLeadToClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ConvertLeadToClientCommand
     * and returns a ConvertLeadToClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ConvertLeadToClientCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertLeadToClientCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(args);

        return new ConvertLeadToClientCommand(index);
    }

}
