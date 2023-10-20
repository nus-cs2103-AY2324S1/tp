package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.Messages.MESSAGE_INVALID_SPECIFIER;

import java.util.ArrayList;
import java.util.List;

import seedu.application.logic.commands.FindCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.FieldContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] specifierAndKeywords = trimmedArgs.split("\\s+");
        List<String> keywords = getKeywordList(specifierAndKeywords);
        String specifier = specifierAndKeywords[0];
        if (!(FieldContainsKeywordsPredicate.isValidSpecifier(specifier))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_SPECIFIER));
        }

        return new FindCommand(new FieldContainsKeywordsPredicate(specifier, keywords));
    }

    /**
     * Takes the given array of which contains the specifier and the keywords
     * and returns a list of keywords.
     */
    private List<String> getKeywordList(String[] specifierAndKeywords) throws ParseException {
        ArrayList<String> keywords = new ArrayList<>();
        for (int i = 1; i < specifierAndKeywords.length; i++) {
            keywords.add(specifierAndKeywords[i]);
        }

        return keywords;
    }

}
