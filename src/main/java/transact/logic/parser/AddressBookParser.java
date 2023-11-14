package transact.logic.parser;

import static transact.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static transact.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import transact.commons.core.LogsCenter;
import transact.logic.commands.AddStaffCommand;
import transact.logic.commands.AddTransactionCommand;
import transact.logic.commands.ClearFilterCommand;
import transact.logic.commands.ClearResultBoxCommand;
import transact.logic.commands.ClearSortCommand;
import transact.logic.commands.ClearStaffCommand;
import transact.logic.commands.ClearTransactionCommand;
import transact.logic.commands.Command;
import transact.logic.commands.DeleteStaffCommand;
import transact.logic.commands.DeleteTransactionCommand;
import transact.logic.commands.EditStaffCommand;
import transact.logic.commands.EditTransactionCommand;
import transact.logic.commands.ExitCommand;
import transact.logic.commands.ExportStaffCommand;
import transact.logic.commands.ExportTransactionCommand;
import transact.logic.commands.FilterCommand;
import transact.logic.commands.FindCommand;
import transact.logic.commands.HelpCommand;
import transact.logic.commands.SortCommand;
import transact.logic.commands.ViewCommand;
import transact.logic.parser.exceptions.ParseException;

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
     * @param userInput
     *            full user input string
     * @return the command based on the user input
     * @throws ParseException
     *             if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level
        // (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {
        case AddStaffCommand.COMMAND_WORD:
            return new AddStaffCommandParser().parse(arguments);

        case AddTransactionCommand.COMMAND_WORD:
            return new AddTransactionCommandParser().parse(arguments);

        case DeleteTransactionCommand.COMMAND_WORD:
            return new DeleteTransactionCommandParser().parse(arguments);

        case EditTransactionCommand.COMMAND_WORD:
            return new EditTransactionCommandParser().parse(arguments);

        case EditStaffCommand.COMMAND_WORD:
            return new EditStaffCommandParser().parse(arguments);

        case DeleteStaffCommand.COMMAND_WORD:
            return new DeleteStaffCommandParser().parse(arguments);

        case ClearStaffCommand.COMMAND_WORD:
            return new ClearStaffCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ClearTransactionCommand.COMMAND_WORD:
            return new ClearTransactionCommand();

        case ClearResultBoxCommand.COMMAND_WORD:
            return new ClearResultBoxCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ClearSortCommand.COMMAND_WORD:
            return new ClearSortCommand();

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case ClearFilterCommand.COMMAND_WORD:
            return new ClearFilterCommand();

        case ExportTransactionCommand.COMMAND_WORD:
            return new ExportTransactionCommandParser().parse(arguments);

        case ExportStaffCommand.COMMAND_WORD:
            return new ExportStaffCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
