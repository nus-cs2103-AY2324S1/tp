package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_MISSING_SECONDARY_COMMAND;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The parser for all secondary {@code add} commands
 */
public class AddCommandParser implements Parser<AddCommand> {

    @Override
    public AddCommand parse(String userInput) throws ParseException {
        String secondaryCommandWord = SecondaryCommandSelector.getSecondaryCommandWord(userInput);

        if (secondaryCommandWord == null) {
            throw new ParseException(String.format(MESSAGE_MISSING_SECONDARY_COMMAND, AddCommand.COMMAND_WORD));
        }

        String args = SecondaryCommandSelector.getArguments(secondaryCommandWord, userInput);
        switch (secondaryCommandWord) {
        case AddPersonCommand.SECONDARY_COMMAND_WORD:
            return new AddPersonCommandParser().parse(args);
        case AddEventCommand.SECONDARY_COMMAND_WORD:
            return new AddEventCommandParser().parse(args);
        case AddNoteCommand.SECONDARY_COMMAND_WORD:
            return new AddNoteCommandParser().parse(args);
        case AddTagCommand.SECONDARY_COMMAND_WORD:
            return new AddTagCommandParser().parse(args);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }


}
