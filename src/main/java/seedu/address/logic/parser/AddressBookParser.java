package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddScheduleCommand;
import seedu.address.logic.commands.AddTutorCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteScheduleCommand;
import seedu.address.logic.commands.DeleteTutorCommand;
import seedu.address.logic.commands.EditScheduleCommand;
import seedu.address.logic.commands.EditTutorCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindScheduleCommand;
import seedu.address.logic.commands.FindTutorCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListScheduleCommand;
import seedu.address.logic.commands.ListTutorCommand;
import seedu.address.logic.commands.MarkScheduleCommand;
import seedu.address.logic.commands.ShowCalendarCommand;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.commands.UnmarkScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

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
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddTutorCommand.COMMAND_WORD:
            return new AddTutorCommandParser().parse(arguments);

        case EditTutorCommand.COMMAND_WORD:
            return new EditTutorCommandParser().parse(arguments);

        case DeleteTutorCommand.COMMAND_WORD:
            return new DeleteTutorCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindTutorCommand.COMMAND_WORD:
            return new FindTutorCommandParser().parse(arguments);

        case ListTutorCommand.COMMAND_WORD:
            return new ListTutorCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddScheduleCommand.COMMAND_WORD:
            return new AddScheduleCommandParser().parse(arguments);

        case EditScheduleCommand.COMMAND_WORD:
            return new EditScheduleCommandParser().parse(arguments);

        case DeleteScheduleCommand.COMMAND_WORD:
            return new DeleteScheduleCommandParser().parse(arguments);

        case ListScheduleCommand.COMMAND_WORD:
            return new ListScheduleCommandParser().parse(arguments);

        case MarkScheduleCommand.COMMAND_WORD:
            return new MarkScheduleCommandParser().parse(arguments);

        case UnmarkScheduleCommand.COMMAND_WORD:
            return new UnmarkScheduleCommandParser().parse(arguments);

        case FindScheduleCommand.COMMAND_WORD:
            return new FindScheduleCommandParser().parse(arguments);

        case ShowCalendarCommand.COMMAND_WORD:
            return new ShowCalendarCommandParser().parse(arguments);

        case ThemeCommand.COMMAND_WORD:
            return new ThemeCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
