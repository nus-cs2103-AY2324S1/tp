package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.util.MenuContainsKeywordsPredicate;




/**
 * Parses input arguments and creates a new FindItemCommand object
 */
public class FindItemCommandParser implements Parser<FindItemCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the FindItemCommand
     * and returns a FindItemCommand object for execution.
     * @param args user input
     * @return FindItemCommand object
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindItemCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            logger.warning("No keywords entered for find-by-item command");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindItemCommand.MESSAGE_USAGE));
        }

        String[] itemKeywords = trimmedArgs.split("\\s+");

        return new FindItemCommand(new MenuContainsKeywordsPredicate(Arrays.asList(itemKeywords)));
    }

}
