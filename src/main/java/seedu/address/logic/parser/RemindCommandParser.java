package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.BirthdayWithinDaysPredicate;
import seedu.address.model.person.EventWithinDaysPredicate;

/**
 * Parses input arguments and creates a new RemindCommand object
 */
public class RemindCommandParser implements Parser<RemindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemindCommand
     * and returns a RemindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemindCommand parse(String args) throws ParseException {
        try {
            int days = ParserUtil.parseDays(args);
            return new RemindCommand(new BirthdayWithinDaysPredicate(days),
                    new EventWithinDaysPredicate(days), days);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE), pe);
        }
    }

}
