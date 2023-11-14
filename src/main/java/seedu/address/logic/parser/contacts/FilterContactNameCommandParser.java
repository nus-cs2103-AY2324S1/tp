package seedu.address.logic.parser.contacts;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.contacts.FilterContactNameCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterContactNameCommand object.
 */
public class FilterContactNameCommandParser implements Parser<FilterContactNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterContactNameCommand
     * and returns a FilterContactNameCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterContactNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterContactNameCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FilterContactNameCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
