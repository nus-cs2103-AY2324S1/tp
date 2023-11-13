package connexion.logic.parser;

import static connexion.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connexion.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.time.Clock;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connexion.commons.core.LogsCenter;
import connexion.logic.commands.AddCommand;
import connexion.logic.commands.ClearCommand;
import connexion.logic.commands.ClearScheduleCommand;
import connexion.logic.commands.Command;
import connexion.logic.commands.DeleteCommand;
import connexion.logic.commands.DetailCommand;
import connexion.logic.commands.EditCommand;
import connexion.logic.commands.ExitCommand;
import connexion.logic.commands.FilterCommand;
import connexion.logic.commands.HelpCommand;
import connexion.logic.commands.ListCommand;
import connexion.logic.commands.MarkCommand;
import connexion.logic.commands.NoteCommand;
import connexion.logic.commands.ScheduleCommand;
import connexion.logic.commands.UnMarkCommand;
import connexion.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser implements ClockDependentParser<Command> {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    private Clock clock = Clock.systemDefaultZone();

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

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().withClock(clock).parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().withClock(clock).parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case DetailCommand.COMMAND_WORD:
            return new DetailCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case NoteCommand.COMMAND_WORD:
            return new NoteCommandParser().withClock(clock).parse(arguments);

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case UnMarkCommand.COMMAND_WORD:
            return new UnMarkCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ScheduleCommand.COMMAND_WORD:
            return new ScheduleCommandParser().parse(arguments);

        case ClearScheduleCommand.COMMAND_WORD:
            return new ClearScheduleCommandParser().withClock(clock).parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * To specify usage of a specific clock.
     *
     * @param clock The clock to use
     * @return an edited parser using the specified clock
     */
    @Override
    public AddressBookParser withClock(Clock clock) {
        AddressBookParser toReturn = new AddressBookParser();
        toReturn.clock = clock;
        return toReturn;
    }


    /**
     * Parses {@code userInput} into a command and returns it. Same as parseCommand.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public Command parse(String userInput) throws ParseException {
        // Suggest : Refactor parseCommand to parse.
        return parseCommand(userInput);
    }
}
