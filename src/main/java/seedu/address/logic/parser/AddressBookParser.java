package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteBuyerCommand;
import seedu.address.logic.commands.DeleteSellerCommand;
import seedu.address.logic.commands.EditBuyerCommand;
import seedu.address.logic.commands.EditSellerCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListBuyerCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListSellerCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SetBuyerPriorityCommand;
import seedu.address.logic.commands.SetSellerPriorityCommand;
import seedu.address.logic.commands.SortBuyerCommand;
import seedu.address.logic.commands.SortSellerCommand;
import seedu.address.logic.commands.UndoCommand;
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
     * @param commandWarnings  A warning container.
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, CommandWarnings commandWarnings) throws ParseException {
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

        switch (commandWord.toLowerCase()) {

        case AddBuyerCommand.COMMAND_WORD:
            return new AddBuyerCommandParser().parse(arguments, commandWarnings);

        case AddSellerCommand.COMMAND_WORD:
            return new AddSellerCommandParser().parse(arguments, commandWarnings);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments, commandWarnings);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListSellerCommand.COMMAND_WORD:
            return new ListSellerCommandParser().parse(arguments, commandWarnings);

        case ListBuyerCommand.COMMAND_WORD:
            return new ListBuyerCommandParser().parse(arguments, commandWarnings);

        case DeleteBuyerCommand.COMMAND_WORD:
            return new DeleteBuyerCommandParser().parse(arguments, commandWarnings);

        case DeleteSellerCommand.COMMAND_WORD:
            return new DeleteSellerCommandParser().parse(arguments, commandWarnings);

        case SetBuyerPriorityCommand.COMMAND_WORD:
            return new SetBuyerPriorityCommandParser().parse(arguments, commandWarnings);

        case SetSellerPriorityCommand.COMMAND_WORD:
            return new SetSellerPriorityCommandParser().parse(arguments, commandWarnings);

        case EditBuyerCommand.COMMAND_WORD:
            return new EditBuyerCommandParser().parse(arguments, commandWarnings);

        case EditSellerCommand.COMMAND_WORD:
            return new EditSellerCommandParser().parse(arguments, commandWarnings);

        case SortBuyerCommand.COMMAND_WORD:
            return new SortBuyerCommandParser().parse(arguments, commandWarnings);

        case SortSellerCommand.COMMAND_WORD:
            return new SortSellerCommandParser().parse(arguments, commandWarnings);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    public Command parseCommand(String userInput) throws ParseException {
        return parseCommand(userInput, new CommandWarnings());
    }

}
