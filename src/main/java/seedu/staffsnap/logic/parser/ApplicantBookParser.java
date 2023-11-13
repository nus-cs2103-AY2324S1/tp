package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.staffsnap.commons.core.LogsCenter;
import seedu.staffsnap.logic.commands.AddCommand;
import seedu.staffsnap.logic.commands.AddInterviewCommand;
import seedu.staffsnap.logic.commands.Command;
import seedu.staffsnap.logic.commands.ConfirmationCommand;
import seedu.staffsnap.logic.commands.DeleteCommand;
import seedu.staffsnap.logic.commands.DeleteInterviewCommand;
import seedu.staffsnap.logic.commands.EditCommand;
import seedu.staffsnap.logic.commands.EditInterviewCommand;
import seedu.staffsnap.logic.commands.ExitCommand;
import seedu.staffsnap.logic.commands.FilterCommand;
import seedu.staffsnap.logic.commands.FindCommand;
import seedu.staffsnap.logic.commands.HelpCommand;
import seedu.staffsnap.logic.commands.ImportCommand;
import seedu.staffsnap.logic.commands.ListCommand;
import seedu.staffsnap.logic.commands.SortCommand;
import seedu.staffsnap.logic.commands.StatusCommand;
import seedu.staffsnap.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ApplicantBookParser {


    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(ApplicantBookParser.class);
    private Boolean isConfirmedNext = false;
    private Boolean isConfirmed = false;


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

        isConfirmed = isConfirmedNext;
        isConfirmedNext = false;
        if (isConfirmed) {
            return new ClearCommandParser().parse(commandWord.toLowerCase());
        }

        switch (commandWord.toLowerCase()) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ConfirmationCommand.COMMAND_WORD:
            isConfirmedNext = true;
            return new ConfirmationCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case AddInterviewCommand.COMMAND_WORD:
            return new AddInterviewCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case EditInterviewCommand.COMMAND_WORD:
            return new EditInterviewCommandParser().parse(arguments);

        case DeleteInterviewCommand.COMMAND_WORD:
            return new DeleteInterviewCommandParser().parse(arguments);

        case StatusCommand.COMMAND_WORD:
            return new StatusCommandParser().parse(arguments);

        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
