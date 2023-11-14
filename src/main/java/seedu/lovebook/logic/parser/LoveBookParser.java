package seedu.lovebook.logic.parser;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.lovebook.commons.core.LogsCenter;
import seedu.lovebook.logic.commands.AddCommand;
import seedu.lovebook.logic.commands.BestMatchCommand;
import seedu.lovebook.logic.commands.BlindDateCommand;
import seedu.lovebook.logic.commands.ClearCommand;
import seedu.lovebook.logic.commands.Command;
import seedu.lovebook.logic.commands.DeleteCommand;
import seedu.lovebook.logic.commands.EditCommand;
import seedu.lovebook.logic.commands.ExitCommand;
import seedu.lovebook.logic.commands.FilterCommand;
import seedu.lovebook.logic.commands.FindCommand;
import seedu.lovebook.logic.commands.HelpCommand;
import seedu.lovebook.logic.commands.ListCommand;
import seedu.lovebook.logic.commands.SetPrefCommand;
import seedu.lovebook.logic.commands.ShowPrefCommand;
import seedu.lovebook.logic.commands.SortCommand;
import seedu.lovebook.logic.commands.StarCommand;
import seedu.lovebook.logic.commands.UnstarCommand;
import seedu.lovebook.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class LoveBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(LoveBookParser.class);

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
            return new HelpCommand();

        case SetPrefCommand.COMMAND_WORD:
            return new SetPrefCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case BlindDateCommand.COMMAND_WORD:
            return new BlindDateCommand();

        case ShowPrefCommand.COMMAND_WORD:
            return new ShowPrefCommand();

        case StarCommand.COMMAND_WORD:
            return new StarCommandParser().parse(arguments);

        case UnstarCommand.COMMAND_WORD:
            return new UnstarCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case BestMatchCommand.COMMAND_WORD:
            return new BestMatchCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
