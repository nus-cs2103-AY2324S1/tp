package wedlog.address.logic.parser;

import static wedlog.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static wedlog.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wedlog.address.commons.core.LogsCenter;
import wedlog.address.logic.commands.Command;
import wedlog.address.logic.commands.GuestAddCommand;
import wedlog.address.logic.commands.GuestDeleteCommand;
import wedlog.address.logic.commands.GuestEditCommand;
import wedlog.address.logic.commands.GuestFilterCommand;
import wedlog.address.logic.commands.GuestListCommand;
import wedlog.address.logic.commands.HelpCommand;
import wedlog.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input specifically for Guest commands.
 */
public class GuestCommandParser {

    public static final String GUEST_COMMAND_WORD = "guest";
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim()); // trims the back and end of the string
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Guest command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {
        case GuestAddCommand.COMMAND_WORD:
            return new GuestAddCommandParser().parse(arguments);

        case GuestEditCommand.COMMAND_WORD:
            return new GuestEditCommandParser().parse(arguments);

        case GuestDeleteCommand.COMMAND_WORD:
            return new GuestDeleteCommandParser().parse(arguments);

        case GuestListCommand.COMMAND_WORD:
            return new GuestListCommand();

        case GuestFilterCommand.COMMAND_WORD:
            return new GuestFilterCommandParser().parse(arguments);

        default:
            logger.finer("This user input for guest command caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
