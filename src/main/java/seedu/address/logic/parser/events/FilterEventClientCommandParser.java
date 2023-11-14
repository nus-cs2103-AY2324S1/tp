package seedu.address.logic.parser.events;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.events.FilterEventClientCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventClientContainsKeywordsPredicate;



/**
 * Parses input arguments and creates a new FilterEventClientCommand object.
 */
public class FilterEventClientCommandParser implements Parser<FilterEventClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterEventClientCommand
     * and returns a FilterEventClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterEventClientCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterEventClientCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FilterEventClientCommand(new EventClientContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
