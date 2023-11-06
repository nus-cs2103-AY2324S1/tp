package seedu.address.logic.parser;

import seedu.address.logic.commands.ListFreeTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Time;

/**
 * Encapsulates the parser for the ListFreeTimeCommand. Arguments parsed is a date.
 *
 * @author Tan Kerway
 */
public class ListFreeTimeCommandParser implements Parser<ListFreeTimeCommand> {
    /**
     * parses the list-freetime command.
     *
     * @author Tan Kerway
     */
    @Override
    public ListFreeTimeCommand parse(String args) throws ParseException {
        try {
            Time givenDay = TimeParser.parseDate(args, true);
            return new ListFreeTimeCommand(givenDay);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(pe.getMessage(),
                            ListFreeTimeCommand.MESSAGE_USAGE), pe);
        }
    }
}
