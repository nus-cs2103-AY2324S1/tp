package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ComponentParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and channels the input argument into the appropriate component parser for handling.
 */
public class ApplicationParser {
    /**
     * Parses the given userInput and passes it to the appropriate component parser for handling.
     *
     * @param userInput user input.
     * @return the command to be executed.
     * @throws ParseException if the user input cannot be parsed successfully.
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");

        switch (commandWord) {
        case AddEventCommand.COMMAND_WORD:
            return new CalendarParser().parseCommand(userInput);
        default:
            return new AddressBookParser().parseCommand(userInput);
        }
    }
}
