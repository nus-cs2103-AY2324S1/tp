package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import connectify.logic.commands.FindPeopleCommand;
import connectify.logic.parser.exceptions.ParseException;
import connectify.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindPeopleCommandParser implements Parser<FindPeopleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPeopleCommand
     * and returns a FindPeopleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPeopleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPeopleCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindPeopleCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
