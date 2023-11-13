package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.ParserUtil.verifyNoArgs;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.AddMeetingContactCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListMeetingCommand;
import seedu.address.logic.commands.MarkMeetingCommand;
import seedu.address.logic.commands.RemoveMeetingContactCommand;
import seedu.address.logic.commands.ViewContactCommand;
import seedu.address.logic.commands.ViewMeetingCommand;
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
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT + "\n" + HelpCommand.MESSAGE_USAGE);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            verifyNoArgs(arguments);
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            verifyNoArgs(arguments);
            return new ListCommand();

        case ListMeetingCommand.COMMAND_WORD:
            verifyNoArgs(arguments);
            return new ListMeetingCommand();

        case ExitCommand.COMMAND_WORD:
            verifyNoArgs(arguments);
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            verifyNoArgs(arguments);
            return new HelpCommand();

        case ViewContactCommand.COMMAND_WORD:
            return new ViewContactCommandParser().parse(arguments);

        case ViewMeetingCommand.COMMAND_WORD:
            return new ViewMeetingCommandParser().parse(arguments);

        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommandParser().parse(arguments);

        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().parse(arguments);

        case EditMeetingCommand.COMMAND_WORD:
            return new EditMeetingCommandParser().parse(arguments);

        case RemoveMeetingContactCommand.COMMAND_WORD:
            return new RemoveMeetingContactCommandParser().parse(arguments);

        case AddMeetingContactCommand.COMMAND_WORD:
            return new AddMeetingContactCommandParser().parse(arguments);

        case FindMeetingCommand.COMMAND_WORD:
            return new FindMeetingCommandParser().parse(arguments);

        case MarkMeetingCommand.COMMAND_WORD:
            return new MarkMeetingCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
