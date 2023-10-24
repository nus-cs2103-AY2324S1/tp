package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.BirthdayWithinDaysPredicate;
import seedu.address.model.person.EventWithinDaysPredicate;

public class RemindCommandParser implements Parser<RemindCommand> {

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
