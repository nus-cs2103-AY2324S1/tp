package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DepartmentCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ManageHrParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(ManageHrParser.class);

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
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case DepartmentCommand.COMMAND_WORD:
            return new DepartmentCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Checks if the command is valid, and creates a fake command to access the usage instructions.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input is not a command
     */
    public Pair<String, String> checkCommandUsage(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Checking command, command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new Pair<String, String>(AddCommand.MESSAGE_USAGE, AddCommand.MESSAGE_EXAMPLE);

        case EditCommand.COMMAND_WORD:
            return new Pair<String, String>(EditCommand.MESSAGE_USAGE, EditCommand.MESSAGE_EXAMPLE);

        case DeleteCommand.COMMAND_WORD:
            return new Pair<String, String>(DeleteCommand.MESSAGE_USAGE, DeleteCommand.MESSAGE_EXAMPLE);

        case ClearCommand.COMMAND_WORD:
            return new Pair<String, String>(ClearCommand.MESSAGE_USAGE, ClearCommand.MESSAGE_EXAMPLE);

        case FindCommand.COMMAND_WORD:
            return new Pair<String, String>(FindCommand.MESSAGE_USAGE, FindCommand.MESSAGE_EXAMPLE);

        case ListCommand.COMMAND_WORD:
            return new Pair<String, String>(ListCommand.MESSAGE_USAGE, ListCommand.MESSAGE_EXAMPLE);

        case ExitCommand.COMMAND_WORD:
            return new Pair<String, String>(ExitCommand.MESSAGE_USAGE, ExitCommand.MESSAGE_EXAMPLE);

        case HelpCommand.COMMAND_WORD:
            return new Pair<String, String>(HelpCommand.MESSAGE_USAGE, HelpCommand.MESSAGE_EXAMPLE);

        case FilterCommand.COMMAND_WORD:
            return new Pair<String, String>(FilterCommand.MESSAGE_USAGE, FilterCommand.MESSAGE_EXAMPLE);

        case DepartmentCommand.COMMAND_WORD:
            return new Pair<String, String>(DepartmentCommand.MESSAGE_USAGE, DepartmentCommand.MESSAGE_EXAMPLE);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

    }

}
