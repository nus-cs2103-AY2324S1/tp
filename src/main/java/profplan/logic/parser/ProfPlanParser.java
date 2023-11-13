package profplan.logic.parser;

import static profplan.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static profplan.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import profplan.commons.core.LogsCenter;
import profplan.logic.commands.AddCommand;
import profplan.logic.commands.ClearCommand;
import profplan.logic.commands.Command;
import profplan.logic.commands.DeleteCommand;
import profplan.logic.commands.DescriptionCommand;
import profplan.logic.commands.DoNextCommand;
import profplan.logic.commands.EditCommand;
import profplan.logic.commands.EditSettingsCommand;
import profplan.logic.commands.ExitCommand;
import profplan.logic.commands.FilterCommand;
import profplan.logic.commands.FindCommand;
import profplan.logic.commands.HelpCommand;
import profplan.logic.commands.ListCommand;
import profplan.logic.commands.ListMonthCommand;
import profplan.logic.commands.ListWeekCommand;
import profplan.logic.commands.MarkCommand;
import profplan.logic.commands.SortDueDateCommand;
import profplan.logic.commands.SortPriorityCommand;
import profplan.logic.commands.StatsCommand;
import profplan.logic.commands.UnmarkCommand;
import profplan.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ProfPlanParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(ProfPlanParser.class);

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

        case EditSettingsCommand.COMMAND_WORD:
            return new EditSettingsCommandParser().parse(arguments);

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DoNextCommand.COMMAND_WORD:
            if (arguments.isBlank()) {
                return new DoNextCommand();
            } else {
                logger.finer("This user input caused a ParseException: " + userInput);
                throw new ParseException("Did you mean the 'do_next' command? Please enter 'do_next' only.");
            }

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            if (arguments.isBlank()) {
                return new ListCommand();
            } else {
                logger.finer("This user input caused a ParseException: " + userInput);
                throw new ParseException("Did you mean the 'list' command? Please enter 'list' only.");
            }

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case DescriptionCommand.COMMAND_WORD:
            return new DescriptionCommandParser().parse(arguments);

        case SortDueDateCommand.COMMAND_WORD:
            if (arguments.isBlank()) {
                return new SortDueDateCommand();
            } else {
                logger.finer("This user input caused a ParseException: " + userInput);
                throw new ParseException("Did you mean the 'sort_duedate' command? Please enter 'sort_duedate' only.");
            }

        case SortPriorityCommand.COMMAND_WORD:
            if (arguments.isBlank()) {
                return new SortPriorityCommand();
            } else {
                logger.finer("This user input caused a ParseException: " + userInput);
                throw new ParseException("Did you mean the 'sort_priority' command? "
                        + "Please enter 'sort_priority' only.");
            }

        case ListWeekCommand.COMMAND_WORD:
            return new ListWeekCommand();

        case ListMonthCommand.COMMAND_WORD:
            return new ListMonthCommand();
        case StatsCommand.COMMAND_WORD:
            return new StatsCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
