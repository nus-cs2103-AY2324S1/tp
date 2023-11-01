package seedu.address.logic.parser;

import java.time.LocalDateTime;

import seedu.address.logic.commands.ListFreeTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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
            LocalDateTime givenDay = TimeParser.parseDate(args, true);
            return new ListFreeTimeCommand(givenDay);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(pe.getMessage(),
                            ListFreeTimeCommand.MESSAGE_USAGE), pe);
        }
    }
}
