package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.SearchPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new SearchPatientCommand object
 */
public class SearchPatientCommandParser implements Parser<SearchPatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchPatientCommand
     * and returns a SearchPatientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchPatientCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchPatientCommand.MESSAGE_USAGE));
        }

        // check input to determine if search by ID or name
        String[] nameKeywords = trimmedArgs.split("\\s+");
        SearchPatientCommand.SearchType searchType = determineSearchType(nameKeywords);

        if (searchType == SearchPatientCommand.SearchType.BY_NAME) {
            return new SearchPatientCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (searchType == SearchPatientCommand.SearchType.BY_ID) {

            if (nameKeywords.length == 1) {
                try {
                    long patientID = Long.parseLong(nameKeywords[0]);
                    System.out.println("Searched patientID: " + patientID);
                    return new SearchPatientCommand(patientID);
                } catch (NumberFormatException e) {
                    throw new ParseException("Patient ID should be a valid number");
                }
            } else {
                throw new ParseException("Invalid search format. Enter a number to search for.");
            }
        } else {
            throw new ParseException("Search cannot be performed. Enter a valid Patient ID or name");
        }
    }

    private SearchPatientCommand.SearchType determineSearchType(String[] nameKeywords) {
        if (nameKeywords.length == 1 && nameKeywords[0].matches("\\d+")) {
            return SearchPatientCommand.SearchType.BY_ID;
        }
        return SearchPatientCommand.SearchType.BY_NAME;
    }
}
