package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_MISSING_SECONDARY_COMMAND;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListEventCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The parser for all secondary {@code list} commands
 */
public class ListCommandParser implements Parser<ListCommand> {
    @Override
    public ListCommand parse(String userInput) throws ParseException {
        String secondaryCommandWord = SecondaryCommandSelector.getSecondaryCommandWord(userInput);

        if (secondaryCommandWord == null) {
            throw new ParseException(String.format(MESSAGE_MISSING_SECONDARY_COMMAND, ListCommand.COMMAND_WORD));
        }

        String args = SecondaryCommandSelector.getArguments(secondaryCommandWord, userInput);
        switch (secondaryCommandWord) {
        case ListPersonCommand.SECONDARY_COMMAND_WORD:
            return new ListPersonCommandParser().parse(args);
        case ListEventCommand.SECONDARY_COMMAND_WORD:
            return new ListEventCommandParser().parse(args);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
