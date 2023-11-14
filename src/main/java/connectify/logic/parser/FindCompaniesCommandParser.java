package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import connectify.logic.commands.FindCompaniesCommand;
import connectify.logic.parser.exceptions.ParseException;
import connectify.model.company.CompanyNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCompaniesCommand object
 */
public class FindCompaniesCommandParser implements Parser<FindCompaniesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCompaniesCommand
     * and returns a FindCompaniesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCompaniesCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCompaniesCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCompaniesCommand(new CompanyNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
