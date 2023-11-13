package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventNameOrGroupContainsKeywordsPredicate;
import seedu.address.model.person.PersonNameOrGroupContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindAllCommand object
 */
public class FindAllCommandParser implements Parser<FindAllCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAllCommand
     * and returns a FindAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAllCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAllCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        List<String> keywordList = Arrays.asList(nameKeywords);

        return new FindAllCommand(new PersonNameOrGroupContainsKeywordsPredicate(keywordList),
                new EventNameOrGroupContainsKeywordsPredicate(keywordList));
    }

}
