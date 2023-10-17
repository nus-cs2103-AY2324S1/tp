package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.staffsnap.commons.core.LogsCenter;
import seedu.staffsnap.logic.commands.AddCommand;
import seedu.staffsnap.logic.commands.AddInterviewCommand;
import seedu.staffsnap.logic.commands.ClearCommand;
import seedu.staffsnap.logic.commands.Command;
import seedu.staffsnap.logic.commands.ConfirmationCommand;
import seedu.staffsnap.logic.commands.DeleteCommand;
import seedu.staffsnap.logic.commands.EditCommand;
import seedu.staffsnap.logic.commands.ExitCommand;
import seedu.staffsnap.logic.commands.FindCommand;
import seedu.staffsnap.logic.commands.HelpCommand;
import seedu.staffsnap.logic.commands.ListCommand;
import seedu.staffsnap.logic.commands.SortCommand;
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
    private Boolean IsConfirmedNext = false;
    private Boolean IsConfirmed = false;


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

        IsConfirmed = IsConfirmedNext;
        IsConfirmedNext = false;

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            if (IsConfirmed) {
                return new ClearCommand();
            } else {
                logger.finer("This user input caused a ParseException: " + userInput);
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        case ConfirmationCommand.COMMAND_WORD:
            IsConfirmedNext = true;
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

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
