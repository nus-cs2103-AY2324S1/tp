package seedu.flashlingo.logic.parser;

import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.flashlingo.logic.commands.FindCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;
import seedu.flashlingo.model.flashcard.WordContainsKeywordsPredicate;


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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new WordContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
