package seedu.address.logic.parser.contacts;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.contacts.FilterContactCompanyCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CompanyContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterContactCompanyCommand object.
 */
public class FilterContactCompanyCommandParser implements Parser<FilterContactCompanyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterContactCompanyCommand
     * and returns a FilterContactCompanyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterContactCompanyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterContactCompanyCommand.MESSAGE_USAGE));
        }

        String[] companyKeywords = trimmedArgs.split("\\s+");

        return new FilterContactCompanyCommand(new CompanyContainsKeywordsPredicate(Arrays.asList(companyKeywords)));
    }

}
