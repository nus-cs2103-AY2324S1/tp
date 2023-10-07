package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ComponentParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class ApplicationParser {
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");

        switch (commandWord) {
        case AddEventCommand.COMMAND_WORD:
            return new CalendarParser().parseCommand(userInput);
        }
        return new AddressBookParser().parseCommand(userInput);
    }
}
