package seedu.address.logic.parser.events;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.commands.events.AddEventCommand;
import seedu.address.logic.commands.events.ClearEventsCommand;
import seedu.address.logic.commands.events.DeleteEventCommand;
import seedu.address.logic.commands.events.EditEventCommand;
import seedu.address.logic.commands.events.FilterEventClientCommand;
import seedu.address.logic.commands.events.FilterEventNameCommand;
import seedu.address.logic.commands.events.FilterEventTimeCommand;
import seedu.address.logic.commands.events.ListAllEventsCommand;
import seedu.address.logic.commands.events.ListEventsCommand;
import seedu.address.logic.parser.ParseCommandHandlers;
import seedu.address.logic.parser.TabCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class EventParser extends ParseCommandHandlers {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(EventParser.class);

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

        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommandParser().parse(arguments);

        case EditEventCommand.COMMAND_WORD:
            return new EditEventCommandParser().parse(arguments);

        case DeleteEventCommand.COMMAND_WORD:
            return new DeleteEventCommandParser().parse(arguments);

        case FilterEventNameCommand.COMMAND_WORD:
            return new FilterEventNameCommandParser().parse(arguments);

        case FilterEventTimeCommand.COMMAND_WORD:
            return new FilterEventTimeCommandParser().parse(arguments);

        case ClearEventsCommand.COMMAND_WORD:
            return new ClearEventsCommand();

        case FilterEventClientCommand.COMMAND_WORD:
            return new FilterEventClientCommandParser().parse(arguments);

        case ListAllEventsCommand.COMMAND_WORD:
            return new ListAllEventsCommand();

        case ListEventsCommand.COMMAND_WORD:
            return new ListEventsCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case TabCommand.COMMAND_WORD:
            return new TabCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
