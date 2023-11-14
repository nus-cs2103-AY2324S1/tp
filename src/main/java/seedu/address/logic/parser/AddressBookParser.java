package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddContactCommand;
import seedu.address.logic.commands.AddContactToMeetingCommand;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.AddMeetingNoteCommand;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteContactCommand;
import seedu.address.logic.commands.DeleteContactFromMeetingCommand;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.logic.commands.DeleteMeetingNoteCommand;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.commands.EditContactCommand;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListContactCommand;
import seedu.address.logic.commands.ListMeetingCommand;
import seedu.address.logic.commands.ModeCommand;
import seedu.address.logic.commands.ViewContactCommand;
import seedu.address.logic.commands.ViewMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.AppState;
import seedu.address.ui.AppState.ModeType;

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
        AppState appState = AppState.getInstance();
        final ModeType mode = appState.getModeType();
        final String commandWord = matcher.group("commandWord").trim();
        final String arguments = " " + matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case ModeCommand.COMMAND_WORD:
            return new ModeCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        default:
            break;
        }

        if (mode == ModeType.CONTACTS) {
            switch (commandWord) {

            case AddContactCommand.COMMAND_WORD:
                return new AddContactCommandParser().parse(arguments);

            case ViewContactCommand.COMMAND_WORD:
                return new ViewContactCommandParser().parse(arguments);

            case EditContactCommand.COMMAND_WORD:
                return new EditContactCommandParser().parse(arguments);

            case DeleteContactCommand.COMMAND_WORD:
                return new DeleteContactCommandParser().parse(arguments);

            case ListContactCommand.COMMAND_WORD:
                return new ListContactCommandParser().parse(arguments);

            case AddNoteCommand.COMMAND_WORD:
                return new AddNoteCommandParser().parse(arguments);

            case DeleteNoteCommand.COMMAND_WORD:
                return new DeleteNoteCommandParser().parse(arguments);

            default:
                break;
            }
        }

        if (mode == ModeType.MEETINGS) {
            switch (commandWord) {

            case AddMeetingCommand.COMMAND_WORD:
                return new AddMeetingCommandParser().parse(arguments);

            case ViewMeetingCommand.COMMAND_WORD:
                return new ViewMeetingCommandParser().parse(arguments);

            case EditMeetingCommand.COMMAND_WORD:
                return new EditMeetingCommandParser().parse(arguments);

            case DeleteMeetingCommand.COMMAND_WORD:
                return new DeleteMeetingCommandParser().parse(arguments);

            case AddContactToMeetingCommand.COMMAND_WORD:
                return new AddContactToMeetingCommandParser().parse(arguments);

            case DeleteContactFromMeetingCommand.COMMAND_WORD:
                return new DeleteContactFromMeetingCommandParser().parse(arguments);

            case ListMeetingCommand.COMMAND_WORD:
                return new ListMeetingCommandParser().parse(arguments);

            case AddMeetingNoteCommand.COMMAND_WORD:
                return new AddMeetingNoteCommandParser().parse(arguments);

            case DeleteMeetingNoteCommand.COMMAND_WORD:
                return new DeleteMeetingNoteCommandParser().parse(arguments);

            default:
                break;
            }
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        throw new ParseException(String.format(MESSAGE_UNKNOWN_COMMAND, userInput));
    }
}
