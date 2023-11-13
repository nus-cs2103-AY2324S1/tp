package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.InvalidFormatException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;

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
            throw new InvalidFormatException(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.COMMAND_WORD,
                    FindCommand.MESSAGE_USAGE);
        }

        List<String> nameKeywords = Arrays.asList(trimmedArgs.split("\\s+"));

        return new FindCommand(new DescriptionContainsKeywordsPredicate(nameKeywords));
    }

}
