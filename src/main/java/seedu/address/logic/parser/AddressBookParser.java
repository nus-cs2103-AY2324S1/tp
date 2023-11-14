package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.AddTagPersonCommand;
import seedu.address.logic.commands.AddTagTaskCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAllDoneCommand;
import seedu.address.logic.commands.DeleteAllPersonCommand;
import seedu.address.logic.commands.DeleteAllTaskCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.DeleteTagPersonCommand;
import seedu.address.logic.commands.DeleteTagTaskCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindAllTagCommand;
import seedu.address.logic.commands.FindDoneCommand;
import seedu.address.logic.commands.FindNotDoneCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAllCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.ListTagCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.commands.MarkTaskCommand;
import seedu.address.logic.commands.UnmarkTaskCommand;
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

        case AddPersonCommand.COMMAND_WORD:
            // Fallthrough
        case AddPersonCommand.SHORTENED_COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            // Fallthrough
        case EditPersonCommand.SHORTENED_COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case AddTagPersonCommand.COMMAND_WORD:
            // Fallthrough
        case AddTagPersonCommand.SHORTENED_COMMAND_WORD:
            return new AddTagPersonCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            // Fallthrough
        case DeletePersonCommand.SHORTENED_COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case DeleteAllPersonCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteAllPersonCommand.SHORTENED_COMMAND_WORD:
            return new DeleteAllPersonCommand();

        case FindPersonCommand.COMMAND_WORD:
            // Fallthrough
        case FindPersonCommand.SHORTENED_COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

        case ListPersonCommand.COMMAND_WORD:
            // Fallthrough
        case ListPersonCommand.SHORTENED_COMMAND_WORD:
            return new ListPersonCommand();

        case ListTaskCommand.COMMAND_WORD:
            // Fallthrough
        case ListTaskCommand.SHORTENED_COMMAND_WORD:
            return new ListTaskCommand();

        case ListAllCommand.COMMAND_WORD:
            // Fallthrough
        case ListAllCommand.SHORTENED_COMMAND_WORD:
            return new ListAllCommand();

        case DeleteTaskCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteTaskCommand.SHORTENED_COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case DeleteAllDoneCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteAllDoneCommand.SHORTENED_COMMAND_WORD:
            return new DeleteAllDoneCommand();

        case MarkTaskCommand.COMMAND_WORD:
            // Fallthrough
        case MarkTaskCommand.SHORTENED_COMMAND_WORD:
            return new MarkTaskCommandParser().parse(arguments);

        case UnmarkTaskCommand.COMMAND_WORD:
            // Fallthrough
        case UnmarkTaskCommand.SHORTENED_COMMAND_WORD:
            return new UnmarkTaskCommandParser().parse(arguments);

        case AddTaskCommand.COMMAND_WORD:
            // Fallthrough
        case AddTaskCommand.SHORTENED_COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            // Fallthrough
        case EditTaskCommand.SHORTENED_COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case AddTagTaskCommand.COMMAND_WORD:
            // Fallthrough
        case AddTagTaskCommand.SHORTENED_COMMAND_WORD:
            return new AddTagTaskCommandParser().parse(arguments);

        case FindTaskCommand.COMMAND_WORD:
            // Fallthrough
        case FindTaskCommand.SHORTENED_COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);

        case FindDoneCommand.COMMAND_WORD:
            // Fallthrough
        case FindDoneCommand.SHORTENED_COMMAND_WORD:
            return new FindDoneCommand();

        case FindNotDoneCommand.COMMAND_WORD:
            // Fallthrough
        case FindNotDoneCommand.SHORTENED_COMMAND_WORD:
            return new FindNotDoneCommand();

        case DeleteAllTaskCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteAllTaskCommand.SHORTENED_COMMAND_WORD:
            return new DeleteAllTaskCommand();

        case FindAllTagCommand.COMMAND_WORD:
            // Fallthrough
        case FindAllTagCommand.SHORTENED_COMMAND_WORD:
            return new FindAllTagCommandParser().parse(arguments);

        case FindTagCommand.COMMAND_WORD:
            // Fallthrough
        case FindTagCommand.SHORTENED_COMMAND_WORD:
            return new FindTagCommandParser().parse(arguments);

        case ListTagCommand.COMMAND_WORD:
            // Fallthrough
        case ListTagCommand.SHORTENED_COMMAND_WORD:
            return new ListTagCommand();

        case DeleteTagPersonCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteTagPersonCommand.SHORTENED_COMMAND_WORD:
            return new DeleteTagPersonCommandParser().parse(arguments);

        case DeleteTagTaskCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteTagTaskCommand.SHORTENED_COMMAND_WORD:
            return new DeleteTagTaskCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            // Fallthrough
        case ExitCommand.SHORTENED_COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            // Fallthrough
        case HelpCommand.SHORTENED_COMMAND_WORD:
            return new HelpCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
