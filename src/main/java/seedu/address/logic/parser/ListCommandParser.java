package seedu.address.logic.parser;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

public class ListCommandParser implements Parser<ListCommand> {
    @Override
    public ListCommand parse(String userInput) throws ParseException {
        String secondaryCommandWord = SecondaryCommandSelector.getSecondaryCommandWord(userInput);
        String args = SecondaryCommandSelector.getArguments(secondaryCommandWord, userInput);
        switch (secondaryCommandWord) {
            case ListPersonCommand.SECONDARY_COMMAND_WORD:
                return new ListPersonCommand();
            case ListEventCommand.SECONDARY_COMMAND_WORD:
                return new ListEventCommand();
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
