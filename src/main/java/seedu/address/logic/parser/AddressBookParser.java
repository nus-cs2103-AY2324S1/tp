package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddInterviewCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteInterviewCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditInterviewCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListFreeTimeCommand;
import seedu.address.logic.commands.ListInterviewCommand;
import seedu.address.logic.commands.ListInterviewsDoneCommand;
import seedu.address.logic.commands.ListInterviewsNotDoneCommand;
import seedu.address.logic.commands.ListInterviewsTodayCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.RateCommand;
import seedu.address.logic.commands.SortRateCommand;
import seedu.address.logic.commands.SortTimeCommand;
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
    @SuppressWarnings("checkstyle:Regexp")
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

        case FindInterviewCommand.COMMAND_WORD:
            return new FindInterviewCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddInterviewCommand.COMMAND_WORD:
            return new AddInterviewCommandParser().parse(arguments);

        case DeleteInterviewCommand.COMMAND_WORD:
            return new DeleteInterviewCommandParser().parse(arguments);

        case RateCommand.COMMAND_WORD:
            return new RateCommandParser().parse(arguments);

        case ListInterviewCommand.COMMAND_WORD:
            return new ListInterviewCommand();

        case ListInterviewsTodayCommand.COMMAND_WORD:
            return new ListInterviewsTodayCommand();

        case ListFreeTimeCommand.COMMAND_WORD:
            return new ListFreeTimeCommandParser().parse(arguments);

        case EditInterviewCommand.COMMAND_WORD:
            return new EditInterviewCommandParser().parse(arguments);

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case SortRateCommand.COMMAND_WORD:
            return new SortRateCommand();

        case SortTimeCommand.COMMAND_WORD:
            return new SortTimeCommand();

        case ListInterviewsDoneCommand.COMMAND_WORD:
            return new ListInterviewsDoneCommand();

        case ListInterviewsNotDoneCommand.COMMAND_WORD:
            return new ListInterviewsNotDoneCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
