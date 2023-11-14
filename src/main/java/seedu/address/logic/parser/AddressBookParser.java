package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNCLEAR_COMMAND;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddPlanCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CompletePlanCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeletePlanCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditPlanCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindPlanCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListPlanCommand;
import seedu.address.logic.commands.UncompletePlanCommand;
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

        String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        String[] allCommands = {AddCommand.COMMAND_WORD, AddPlanCommand.COMMAND_WORD, ClearCommand.COMMAND_WORD,
                                CompletePlanCommand.COMMAND_WORD, UncompletePlanCommand.COMMAND_WORD,
                                DeleteCommand.COMMAND_WORD, DeletePlanCommand.COMMAND_WORD, EditCommand.COMMAND_WORD,
                                EditPlanCommand.COMMAND_WORD, ExitCommand.COMMAND_WORD, FindCommand.COMMAND_WORD,
                                HelpCommand.COMMAND_WORD, ListCommand.COMMAND_WORD, ListPlanCommand.COMMAND_WORD,
                                FindPlanCommand.COMMAND_WORD};
        String[] unclearCommand = {"add", "delete", "edit", "find", "list"};

        boolean isValidCommand = Arrays.stream(allCommands).anyMatch(commandWord::matches);
        boolean isUnclearCommand = Arrays.stream(unclearCommand).anyMatch(commandWord::matches);

        if (isUnclearCommand) {
            commandWord = "unclear";
        } else if (!isValidCommand) {
            commandWord = "unknown";
        }

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

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddPlanCommand.COMMAND_WORD:
            return new AddPlanCommandParser().parse(arguments);

        case ListPlanCommand.COMMAND_WORD:
            return new ListPlanCommand();

        case DeletePlanCommand.COMMAND_WORD:
            return new DeletePlanCommandParser().parse(arguments);

        case CompletePlanCommand.COMMAND_WORD:
            return new CompletePlanCommandParser().parse(arguments);

        case EditPlanCommand.COMMAND_WORD:
            return new EditPlanCommandParser().parse(arguments);

        case UncompletePlanCommand.COMMAND_WORD:
            return new UncompletePlanCommandParser().parse(arguments);

        case FindPlanCommand.COMMAND_WORD:
            return new FindPlanCommandParser().parse(arguments);

        case "unclear":
            throw new ParseException(MESSAGE_UNCLEAR_COMMAND);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
