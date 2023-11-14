package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCustomerCommand;
import seedu.address.logic.commands.DeletePropertyCommand;
import seedu.address.logic.commands.EditCustomerCommand;
import seedu.address.logic.commands.EditPropertyCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCustomerCommand;
import seedu.address.logic.commands.FilterPropertyCommand;
import seedu.address.logic.commands.FindCustomerCommand;
import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCustomerCommand;
import seedu.address.logic.commands.ListPropertyCommand;
import seedu.address.logic.commands.MatchCustomerCommand;
import seedu.address.logic.commands.MatchPropertyCommand;
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

        case AddCustomerCommand.COMMAND_WORD:
            return new AddCustomerCommandParser().parse(arguments);

        case AddPropertyCommand.COMMAND_WORD:
            return new AddPropertyCommandParser().parse(arguments);

        case EditCustomerCommand.COMMAND_WORD:
            return new EditCustomerCommandParser().parse(arguments);

        case EditPropertyCommand.COMMAND_WORD:
            return new EditPropertyCommandParser().parse(arguments);

        case DeletePropertyCommand.COMMAND_WORD:
            return new DeletePropertyCommandParser().parse(arguments);

        case DeleteCustomerCommand.COMMAND_WORD:
            return new DeleteCustomerCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCustomerCommand.COMMAND_WORD:
            return new FindCustomerCommandParser().parse(arguments);

        case FindPropertyCommand.COMMAND_WORD:
            return new FindPropertyCommandParser().parse(arguments);

        case FilterCustomerCommand.COMMAND_WORD:
            return new FilterCustomerCommandParser().parse(arguments);

        case FilterPropertyCommand.COMMAND_WORD:
            return new FilterPropertyCommandParser().parse(arguments);

        case ListCustomerCommand.COMMAND_WORD:
            return new ListCustomerCommand();

        case ListPropertyCommand.COMMAND_WORD:
            return new ListPropertyCommand();

        case MatchCustomerCommand.COMMAND_WORD:
            return new MatchCustomerCommandParser().parse(arguments);

        case MatchPropertyCommand.COMMAND_WORD:
            return new MatchPropertyCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
