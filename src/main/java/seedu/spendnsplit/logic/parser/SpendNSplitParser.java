package seedu.spendnsplit.logic.parser;

import static seedu.spendnsplit.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.spendnsplit.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.spendnsplit.commons.core.LogsCenter;
import seedu.spendnsplit.logic.commands.AddPersonCommand;
import seedu.spendnsplit.logic.commands.AddTransactionCommand;
import seedu.spendnsplit.logic.commands.ClearCommand;
import seedu.spendnsplit.logic.commands.Command;
import seedu.spendnsplit.logic.commands.DeletePersonCommand;
import seedu.spendnsplit.logic.commands.DeleteTransactionCommand;
import seedu.spendnsplit.logic.commands.DuplicateTransactionCommand;
import seedu.spendnsplit.logic.commands.EditPersonCommand;
import seedu.spendnsplit.logic.commands.EditTransactionCommand;
import seedu.spendnsplit.logic.commands.ExitCommand;
import seedu.spendnsplit.logic.commands.HelpCommand;
import seedu.spendnsplit.logic.commands.ListPersonCommand;
import seedu.spendnsplit.logic.commands.ListTransactionCommand;
import seedu.spendnsplit.logic.commands.SetShorthandCommand;
import seedu.spendnsplit.logic.commands.SettlePersonCommand;
import seedu.spendnsplit.logic.commands.SortPersonCommand;
import seedu.spendnsplit.logic.commands.UpdatePortionCommand;
import seedu.spendnsplit.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class SpendNSplitParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(SpendNSplitParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, CommandAliasMap commandMap) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = commandMap.getCommand(matcher.group("commandWord"));
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddPersonCommand.COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ListPersonCommand.COMMAND_WORD:
            return new ListPersonCommandParser().parse(arguments);

        case SettlePersonCommand.COMMAND_WORD:
            return new SettlePersonCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListTransactionCommand.COMMAND_WORD:
            return new ListTransactionCommandParser().parse(arguments);

        case AddTransactionCommand.COMMAND_WORD:
            return new AddTransactionCommandParser().parse(arguments);

        case EditTransactionCommand.COMMAND_WORD:
            return new EditTransactionCommandParser().parse(arguments);
        case UpdatePortionCommand.COMMAND_WORD:
            return new UpdatePortionCommandParser().parse(arguments);

        case DeleteTransactionCommand.COMMAND_WORD:
            return new DeleteTransactionCommandParser().parse(arguments);

        case DuplicateTransactionCommand.COMMAND_WORD:
            return new DuplicateTransactionCommandParser().parse(arguments);

        case SetShorthandCommand.COMMAND_WORD:
            return new SetShorthandCommandParser().parse(arguments);

        case SortPersonCommand.COMMAND_WORD:
            return new SortPersonCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
