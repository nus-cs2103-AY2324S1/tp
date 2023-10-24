package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ShowCalendarCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.Date;



/**
 * Parses input arguments and creates a new ShowCalendarCommand object
 */
public class ShowCalendarCommandParser implements Parser<ShowCalendarCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ShowCalendarCommand
     * and returns an ShowCalendarCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowCalendarCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            Date date = ParserUtil.parseDate(args);
            return new ShowCalendarCommand(date);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCalendarCommand.MESSAGE_USAGE), pe);
        }
    }
}
