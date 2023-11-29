package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddDentistCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.AddTreatmentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteDentistCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.DeleteTreatmentCommand;
import seedu.address.logic.commands.EditDentistCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterAppointmentCommand;
import seedu.address.logic.commands.FilterDentistCommand;
import seedu.address.logic.commands.FilterPatientCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.commands.ListDentistCommand;
import seedu.address.logic.commands.ListPatientCommand;
import seedu.address.logic.commands.ListTreatmentCommand;
import seedu.address.logic.commands.SearchDentistCommand;
import seedu.address.logic.commands.SearchPatientCommand;
import seedu.address.logic.commands.ViewCalendarCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
        "(?<commandWord>\\S+)(?<arguments>.*)");
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
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddDentistCommand.COMMAND_WORD:
            return new AddDentistCommandParser().parse(arguments);

        case EditDentistCommand.COMMAND_WORD:
            return new EditDentistCommandParser().parse(arguments);

        case EditPatientCommand.COMMAND_WORD:
            return new EditPatientCommandParser().parse(arguments);

        case DeletePatientCommand.COMMAND_WORD:
            return new DeletePatientCommandParser().parse(arguments);

        case DeleteDentistCommand.COMMAND_WORD:
            return new DeleteDentistCommandParser().parse(arguments);

        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case DeleteTreatmentCommand.COMMAND_WORD:
            return new DeleteTreatmentCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FilterPatientCommand.COMMAND_WORD:
            return new FilterPatientCommandParser().parse(arguments);

        case FilterDentistCommand.COMMAND_WORD:
            return new FilterDentistCommandParser().parse(arguments);

        case SearchDentistCommand.COMMAND_WORD:
            return new SearchDentistCommandParser().parse(arguments);

        case SearchPatientCommand.COMMAND_WORD:
            return new SearchPatientCommandParser().parse(arguments);

        case FilterAppointmentCommand.COMMAND_WORD:
            return new FilterAppointmentCommandParser().parse(arguments);

        case ListPatientCommand.COMMAND_WORD:
            return new ListPatientCommand();

        case ListDentistCommand.COMMAND_WORD:
            return new ListDentistCommand();

        case ListTreatmentCommand.COMMAND_WORD:
            return new ListTreatmentCommand();

        case ListAppointmentCommand.COMMAND_WORD:
            return new ListAppointmentCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ViewCalendarCommand.COMMAND_WORD:
            return new ViewCalendarCommand();

        case AddPatientCommand.COMMAND_WORD:
            return new AddPatientCommandParser().parse(arguments);

        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case AddTreatmentCommand.COMMAND_WORD:
            return new AddTreatmentCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
