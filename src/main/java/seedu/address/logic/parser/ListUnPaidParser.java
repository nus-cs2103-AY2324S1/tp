package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListByDayCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListUnPaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Day;
import seedu.address.model.person.DayPredicate;
import seedu.address.model.person.PaidPredicate;

public class ListUnPaidParser implements Parser<ListCommand> {

    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListCommand();
        }

        boolean paid;
        paid = false;

        /*try {
            paid = new Day(trimmedArgs);
        } catch (IllegalArgumentException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListByDayCommand.MESSAGE_USAGE));
        }*/

        ListUnPaidCommand listUnPaidCommand = new ListUnPaidCommand(new PaidPredicate(paid));
        return listUnPaidCommand;
    }
}
