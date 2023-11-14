package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventDetailsCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ClearEventsCommand;
import seedu.address.logic.commands.ClearGuestsCommand;
import seedu.address.logic.commands.ClearVendorsCommand;
import seedu.address.logic.commands.ClearVenuesCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CreateEventCommand;
import seedu.address.logic.commands.CreateVendorCommand;
import seedu.address.logic.commands.CreateVenueCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.commands.DeleteVendorCommand;
import seedu.address.logic.commands.DeleteVenueCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditVendorCommand;
import seedu.address.logic.commands.EditVenueCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindEventCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemovePersonCommand;
import seedu.address.logic.commands.RemoveVendorCommand;
import seedu.address.logic.commands.RsvpCommand;
import seedu.address.logic.commands.ViewEventCommand;
import seedu.address.logic.commands.ViewEventsCommand;
import seedu.address.logic.commands.ViewVendorsCommand;
import seedu.address.logic.commands.ViewVenuesCommand;
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

        case AddEventDetailsCommand.COMMAND_WORD:
            return new AddEventDetailsCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearGuestsCommand.COMMAND_WORD:
            return new ClearGuestsCommand();

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

        case CreateEventCommand.COMMAND_WORD:
            return new CreateEventCommandParser().parse(arguments);

        case EditEventCommand.COMMAND_WORD:
            return new EditEventCommandParser().parse(arguments);

        case ViewEventCommand.COMMAND_WORD:
            return new ViewEventCommandParser().parse(arguments);

        case ViewEventsCommand.COMMAND_WORD:
            return new ViewEventsCommand();

        case DeleteEventCommand.COMMAND_WORD:
            return new DeleteEventCommandParser().parse(arguments);

        case CreateVenueCommand.COMMAND_WORD:
            return new CreateVenueCommandParser().parse(arguments);

        case EditVenueCommand.COMMAND_WORD:
            return new EditVenueCommandParser().parse(arguments);

        case DeleteVenueCommand.COMMAND_WORD:
            return new DeleteVenueCommandParser().parse(arguments);

        case ClearEventsCommand.COMMAND_WORD:
            return new ClearEventsCommand();

        case FindEventCommand.COMMAND_WORD:
            return new FindEventCommandParser().parse(arguments);

        case ViewVenuesCommand.COMMAND_WORD:
            return new ViewVenuesCommand();

        case ClearVenuesCommand.COMMAND_WORD:
            return new ClearVenuesCommand();

        case RsvpCommand.COMMAND_WORD:
            return new RsvpCommandParser().parse(arguments);

        case CreateVendorCommand.COMMAND_WORD:
            return new CreateVendorCommandParser().parse(arguments);

        case EditVendorCommand.COMMAND_WORD:
            return new EditVendorCommandParser().parse(arguments);

        case ViewVendorsCommand.COMMAND_WORD:
            return new ViewVendorsCommand();

        case RemovePersonCommand.COMMAND_WORD:
            return new RemovePersonCommandParser().parse(arguments);

        case RemoveVendorCommand.COMMAND_WORD:
            return new RemoveVendorCommandParser().parse(arguments);

        case DeleteVendorCommand.COMMAND_WORD:
            return new DeleteVendorCommandParser().parse(arguments);

        case ClearVendorsCommand.COMMAND_WORD:
            return new ClearVendorsCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
