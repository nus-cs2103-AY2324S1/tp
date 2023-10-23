package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class RemindCommandParser implements Parser<RemindCommand> {

    public RemindCommand parse(String args) throws ParseException {
        try {
            int days = ParserUtil.parseDays(args);
            return new RemindCommand(days);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE), pe);
        }
    }

}
