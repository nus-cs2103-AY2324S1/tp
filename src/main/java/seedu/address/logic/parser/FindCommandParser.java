package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.IdContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        // change args.trim to start from after n/ or after /id
        String trimmedArgs = args.trim();
        String[] nameKeywords = null;
        String[] idKeywords = null;
        if (trimmedArgs.startsWith("n/")) {
            nameKeywords = trimmedArgs.substring(2).split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (trimmedArgs.startsWith("id/")) {
            idKeywords = trimmedArgs.substring(3).split("\\s+");
            return new FindCommand(new IdContainsKeywordsPredicate(Arrays.asList(idKeywords)));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}

