package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

public class AddCommandParser implements Parser<AddCommand> {

    @Override
    public AddCommand parse(String userInput) throws ParseException {
        String secondaryCommandWord = SecondaryCommandSelector.getSecondaryCommandWord(userInput);
        String args = SecondaryCommandSelector.getArguments(secondaryCommandWord, userInput);
        switch (secondaryCommandWord) {
            case AddPersonCommand.SECONDARY_COMMAND_WORD:
                return new AddPersonCommandParser().parse(args);
            case AddEventCommand.SECONDARY_COMMAND_WORD:
                return new AddEventCommandParser().parse(args);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }


}
