package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.commands.finance.AddCommissionCommand;
import seedu.address.logic.commands.finance.AddExpenseCommand;
import seedu.address.logic.commands.finance.ClearFinancesCommand;
import seedu.address.logic.commands.finance.DeleteFinanceCommand;
import seedu.address.logic.commands.finance.EditFinanceCommand;
import seedu.address.logic.commands.finance.FilterClientNameCommand;
import seedu.address.logic.commands.finance.FilterTimeDueCommand;
import seedu.address.logic.commands.finance.ListFinancesCommand;
import seedu.address.logic.commands.finance.SummaryCommand;
import seedu.address.logic.parser.ParseCommandHandlers;
import seedu.address.logic.parser.TabCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input in the Finance Tab.
 */
public class FinanceParser extends ParseCommandHandlers {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(FinanceParser.class);

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
        case AddCommissionCommand.COMMAND_WORD:
            return new AddCommissionCommandParser().parse(arguments);
        case AddExpenseCommand.COMMAND_WORD:
            return new AddExpenseCommandParser().parse(arguments);
        case DeleteFinanceCommand.COMMAND_WORD:
            return new DeleteFinanceCommandParser().parse(arguments);
        case ListFinancesCommand.COMMAND_WORD:
            return new ListFinanceParser().parse(arguments);
        case EditFinanceCommand.COMMAND_WORD:
            return new EditFinanceCommandParser().parse(arguments);
        case TabCommand.COMMAND_WORD:
            return new TabCommandParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case FilterClientNameCommand.COMMAND_WORD:
            return new FilterClientNameCommandParser().parse(arguments);
        case FilterTimeDueCommand.COMMAND_WORD:
            return new FilterTimeDueCommandParser().parse(arguments);
        case SummaryCommand.COMMAND_WORD:
            return new SummaryCommandParser().parse(arguments);
        case ClearFinancesCommand.COMMAND_WORD:
            return new ClearFinancesCommand();
        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
