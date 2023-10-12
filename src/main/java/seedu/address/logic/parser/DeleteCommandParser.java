package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The parser for all secondary {@code delete} commands
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    @Override
    public DeleteCommand parse(String userInput) throws ParseException {
        String secondaryCommandWord = SecondaryCommandSelector.getSecondaryCommandWord(userInput);
        String args = SecondaryCommandSelector.getArguments(secondaryCommandWord, userInput);
        switch (secondaryCommandWord) {
        case DeletePersonCommand.SECONDARY_COMMAND_WORD:
            return new DeletePersonCommandParser().parse(args);
        case DeleteEventCommand.SECONDARY_COMMAND_WORD:
            return new DeleteEventCommandParser().parse(args);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
