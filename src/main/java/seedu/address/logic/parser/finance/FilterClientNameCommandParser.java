package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.finance.FilterClientNameCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.ClientNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterClientNameCommand object.
 */
public class FilterClientNameCommandParser implements Parser<FilterClientNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterClientNameCommand
     * and returns a FilterClientNameCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterClientNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterClientNameCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FilterClientNameCommand(new ClientNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
