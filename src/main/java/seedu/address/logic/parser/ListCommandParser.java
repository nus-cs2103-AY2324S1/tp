package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListByDayCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListUnPaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Day;
import seedu.address.model.person.DayPredicate;
import seedu.address.model.person.PaidPredicate;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a corresponding ListCommand, or its subtype, object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListCommand();
        }

        if (trimmedArgs.startsWith("unpaid")) {
            return new ListUnPaidCommand(new PaidPredicate(true));
        }

        Day day;

        try {
            day = new Day(trimmedArgs);
        } catch (IllegalArgumentException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListByDayCommand.MESSAGE_USAGE));
        }

        return new ListByDayCommand(new DayPredicate(day));
    }
}
