package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.SearchDentistCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new SearchDentistCommand object
 */
public class SearchDentistCommandParser implements Parser<SearchDentistCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchDentistCommand
     * and returns a SearchDentistCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchDentistCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchDentistCommand.MESSAGE_USAGE));
        }

        // check input to determine if search by ID or name
        // Solution adapted and inspired from https://chat.openai.com/share/c95e431a-4406-4a1e-b88f-cbff729f1cca
        String[] nameKeywords = trimmedArgs.split("\\s+");
        SearchDentistCommand.SearchType searchType = determineSearchType(nameKeywords);

        if (searchType == SearchDentistCommand.SearchType.BY_NAME) {
            return new SearchDentistCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (searchType == SearchDentistCommand.SearchType.BY_ID) {

            if (nameKeywords.length == 1) {
                try {
                    long dentistID = Long.parseLong(nameKeywords[0]);
                    System.out.println("Searched dentistID: " + dentistID);
                    return new SearchDentistCommand(dentistID);
                } catch (NumberFormatException e) {
                    throw new ParseException("Dentist ID should be a valid number");
                }
            } else {
                throw new ParseException("Invalid search format. Enter a number to search for.");
            }
        } else {
            throw new ParseException("Search cannot be performed. Enter a valid Dentist ID or name");
        }
    }

    private SearchDentistCommand.SearchType determineSearchType(String[] nameKeywords) {
        if (nameKeywords.length == 1 && nameKeywords[0].matches("\\d+")) {
            return SearchDentistCommand.SearchType.BY_ID;
        }
        return SearchDentistCommand.SearchType.BY_NAME;
    }
}
