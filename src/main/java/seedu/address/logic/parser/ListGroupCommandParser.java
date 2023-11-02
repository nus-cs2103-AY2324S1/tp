package seedu.address.logic.parser;

import seedu.address.logic.commands.ListGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ListGroupCommandParser implements Parser<ListGroupCommand> {
    @Override
    public ListGroupCommand parse(String args) throws ParseException {
        if (args.length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListGroupCommand.MESSAGE_USAGE));
        }

        return new ListGroupCommand();
    }
}
