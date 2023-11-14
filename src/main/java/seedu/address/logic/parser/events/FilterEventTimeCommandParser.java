package seedu.address.logic.parser.events;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeInstance;

import seedu.address.logic.commands.events.FilterEventTimeCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventTimeBeforePredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FilterEventTimeCommandParser implements Parser<FilterEventTimeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterEventTimeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterEventTimeCommand.MESSAGE_USAGE));
        }
        return new FilterEventTimeCommand(new EventTimeBeforePredicate(parseDateTimeInstance(trimmedArgs)));
    }


}
