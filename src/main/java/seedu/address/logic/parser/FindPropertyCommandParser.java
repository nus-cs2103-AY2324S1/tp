package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.PropNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindPropertyCommandParser implements Parser<FindPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPropertyCommand
     * and returns a FindPropertyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPropertyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindPropertyCommand(new PropNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
