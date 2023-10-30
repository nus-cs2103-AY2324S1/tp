package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditFieldCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.SaveCommand;
import seedu.address.logic.commands.ViewExitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Parses user input while in the fosterer profile view page.
 */
public class ViewModeParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>.*)");
    private static final Logger logger = LogsCenter.getLogger(seedu.address.logic.parser.AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, Person newPerson, Index targetIndex) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord);

        switch (commandWord) {

        case SaveCommand.SAVE_COMMAND_WORD:
            if (targetIndex != null) {
                return new SaveCommand(targetIndex, newPerson);
            } else {
                return new AddCommand(newPerson);
            }
        case ViewExitCommand.COMMAND_WORD:
            return new ViewExitCommand();

        default:
            return new EditFieldCommand();
        }


    }
}
