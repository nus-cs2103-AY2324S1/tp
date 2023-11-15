package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddItemCommand;
import seedu.address.logic.commands.AddItemReviewCommand;
import seedu.address.logic.commands.AddStallCommand;
import seedu.address.logic.commands.AddStallReviewCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteItemCommand;
import seedu.address.logic.commands.DeleteItemReviewCommand;
import seedu.address.logic.commands.DeleteStallCommand;
import seedu.address.logic.commands.DeleteStallReviewCommand;
import seedu.address.logic.commands.EditItemCommand;
import seedu.address.logic.commands.EditStallCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindItemCommand;
import seedu.address.logic.commands.FindLocationCommand;
import seedu.address.logic.commands.FindStallCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SortStallLocationCommand;
import seedu.address.logic.commands.SortStallPriceCommand;
import seedu.address.logic.commands.SortStallRatingCommand;
import seedu.address.logic.commands.ViewItemCommand;
import seedu.address.logic.commands.ViewStallCommand;
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

        case AddStallCommand.COMMAND_WORD:
            return new AddStallCommandParser().parse(arguments);

        case EditStallCommand.COMMAND_WORD:
            return new EditStallCommandParser().parse(arguments);

        case DeleteStallCommand.COMMAND_WORD:
            return new DeleteStallCommandParser().parse(arguments);

        case ViewItemCommand.COMMAND_WORD:
            return new ViewItemCommandParser().parse(arguments);

        case AddItemCommand.COMMAND_WORD:
            return new AddItemCommandParser().parse(arguments);

        case EditItemCommand.COMMAND_WORD:
            return new EditItemCommandParser().parse(arguments);

        case DeleteItemCommand.COMMAND_WORD:
            return new DeleteItemCommandParser().parse(arguments);

        case AddItemReviewCommand.COMMAND_WORD:
            return new AddItemReviewCommandParser().parse(arguments);

        case DeleteItemReviewCommand.COMMAND_WORD:
            return new DeleteItemReviewCommandParser().parse(arguments);

        case DeleteStallReviewCommand.COMMAND_WORD:
            return new DeleteStallReviewCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindItemCommand.COMMAND_WORD:
            return new FindItemCommandParser().parse(arguments);

        case FindLocationCommand.COMMAND_WORD:
            return new FindLocationCommandParser().parse(arguments);

        case FindStallCommand.COMMAND_WORD:
            return new FindStallCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case SortStallRatingCommand.COMMAND_WORD:
            return new SortStallRatingCommand();

        case SortStallLocationCommand.COMMAND_WORD:
            return new SortStallLocationCommand();

        case SortStallPriceCommand.COMMAND_WORD:
            return new SortStallPriceCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddStallReviewCommand.COMMAND_WORD:
            return new AddStallReviewCommandParser().parse(arguments);

        case ViewStallCommand.COMMAND_WORD:
            return new ViewStallCommandParser().parse(arguments);


        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
