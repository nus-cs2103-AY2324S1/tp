package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindStallCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.util.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindStallCommand object
 */
public class FindStallCommandParser implements Parser<FindStallCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the FindStallCommand
     * and returns a FindStallCommand object for execution.
     * @param args user input
     * @return FindStallCommand object
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStallCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            logger.warning("No keywords entered for find-by-stall command");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStallCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindStallCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
