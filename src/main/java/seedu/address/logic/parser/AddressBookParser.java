package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_NO_ARGUMENTS_EXPECTED;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BirthdayCommand;
import seedu.address.logic.commands.ClaimCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LeaveCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ResetLeavesCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ViewLeaveCommand;
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
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            ensureEmptyArguments(arguments, ClearCommand.COMMAND_WORD);
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            ensureEmptyArguments(arguments, ExitCommand.COMMAND_WORD);
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            ensureEmptyArguments(arguments, HelpCommand.COMMAND_WORD);
            return new HelpCommand();

        case ClaimCommand.COMMAND_WORD:
            return new ClaimCommandParser().parse(arguments);

        case LeaveCommand.COMMAND_WORD:
            return new LeaveCommandParser().parse(arguments);

        case ViewLeaveCommand.COMMAND_WORD:
            return new ViewLeaveCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case BirthdayCommand.COMMAND_WORD:
            return new BirthdayCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            ensureEmptyArguments(arguments, UndoCommand.COMMAND_WORD);
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            ensureEmptyArguments(arguments, RedoCommand.COMMAND_WORD);
            return new RedoCommand();

        case ThemeCommand.COMMAND_WORD:
            return new ThemeCommandParser().parse(arguments);

        case ExportCommand.COMMAND_WORD:
            return new ExportCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ResetLeavesCommand.COMMAND_WORD:
            ensureEmptyArguments(arguments, ResetLeavesCommand.COMMAND_WORD);
            return new ResetLeavesCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Ensures that there are no arguments given.
     *
     * @param args argument(s) given
     * @param commandWord commandWord of the checked command
     * @throws ParseException if the argument is not empty
     */
    public void ensureEmptyArguments(String args, String commandWord) throws ParseException {
        if (!args.isBlank()) {
            throw new ParseException(String.format(MESSAGE_NO_ARGUMENTS_EXPECTED, commandWord));
        }
    }
}
