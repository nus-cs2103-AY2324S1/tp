package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddDevCommand;
import seedu.address.logic.commands.AddDevToTeamCommand;
import seedu.address.logic.commands.AddTeamCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteDeveloperFromTeamCommand;
import seedu.address.logic.commands.DeleteTeamCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditTeamLeaderCommand;
import seedu.address.logic.commands.EditTeamNameCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindTeamCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListTeamCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.TreeCommand;
import seedu.address.logic.commands.WelcomeCommand;
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

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddDevCommand.COMMAND_WORD:
            return new AddDevCommandParser().parse(arguments);

        case AddTeamCommand.COMMAND_WORD:
            return new AddTeamCommandParser().parse(arguments);

        case AddDevToTeamCommand.COMMAND_WORD:
            return new AddDevToTeamParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case EditTeamNameCommand.COMMAND_WORD:
            return new EditTeamNameCommandParser().parse(arguments);

        case EditTeamLeaderCommand.COMMAND_WORD:
            return new EditTeamLeaderCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteTeamCommand.COMMAND_WORD:
            return new DeleteTeamCommandParser().parse(arguments);

        case DeleteDeveloperFromTeamCommand.COMMAND_WORD:
            return new DeleteDeveloperFromTeamCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindTeamCommand.COMMAND_WORD:
            return new FindTeamCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListTeamCommand.COMMAND_WORD:
            return new ListTeamCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case WelcomeCommand.COMMAND_WORD:
            return new WelcomeCommand();

        case TreeCommand.COMMAND_WORD:
            return new TreeCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
