package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddAffiliationCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AffiliationCommand;
import seedu.address.logic.commands.AffiliationHistoryCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.InfoCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.NextOfKinCommand;
import seedu.address.logic.commands.OnDutyCommand;
import seedu.address.logic.commands.RemoveAffiliationHistoryCommand;
import seedu.address.logic.commands.ShiftCommand;
import seedu.address.logic.commands.SpecialisationCommand;
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

        case AffiliationCommand.COMMAND_WORD:
            return new AffiliationCommandParser().parse(arguments);

        case AffiliationHistoryCommand.COMMAND_WORD:
            return new AffiliationHistoryCommandParser().parse(arguments);

        case AddAffiliationCommand.COMMAND_WORD:
            return new AddAffiliationCommandParser().parse(arguments);

        case ShiftCommand.COMMAND_WORD:
            return new ShiftCommandParser().parse(arguments);

        case RemoveAffiliationHistoryCommand.COMMAND_WORD:
            return new RemoveAffiliationHistoryCommandParser().parse(arguments);

        case OnDutyCommand.COMMAND_WORD:
            return new OnDutyCommand();

        case SpecialisationCommand.COMMAND_WORD:
            return new SpecialisationCommandParser().parse(arguments);

        case InfoCommand.COMMAND_WORD:
            return new InfoCommandParser().parse(arguments);

        case NextOfKinCommand.COMMAND_WORD:
            return new NextOfKinCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
