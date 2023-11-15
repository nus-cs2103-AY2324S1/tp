package seedu.cc.logic.parser;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.cc.commons.core.LogsCenter;
import seedu.cc.logic.commands.AddCommand;
import seedu.cc.logic.commands.ClearCommand;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.DeleteCommand;
import seedu.cc.logic.commands.EditCommand;
import seedu.cc.logic.commands.ExitCommand;
import seedu.cc.logic.commands.FindCommand;
import seedu.cc.logic.commands.HelpCommand;
import seedu.cc.logic.commands.ListCommand;
import seedu.cc.logic.commands.SwitchCommand;
import seedu.cc.logic.commands.appointmentcommands.AddAppointmentEventCommand;
import seedu.cc.logic.commands.appointmentcommands.AddPrescriptionCommand;
import seedu.cc.logic.commands.appointmentcommands.DeleteAppointmentEventCommand;
import seedu.cc.logic.commands.appointmentcommands.DeletePrescriptionCommand;
import seedu.cc.logic.commands.appointmentcommands.EditAppointmentEventCommand;
import seedu.cc.logic.commands.appointmentcommands.EditPrescriptionCommand;
import seedu.cc.logic.commands.appointmentcommands.ListAppointmentEventsCommand;
import seedu.cc.logic.commands.medhisteventcommands.AddMedicalHistoryEventCommand;
import seedu.cc.logic.commands.medhisteventcommands.DeleteMedicalHistoryEventCommand;
import seedu.cc.logic.commands.medhisteventcommands.EditMedicalHistoryEventCommand;
import seedu.cc.logic.commands.medhisteventcommands.ListMedicalHistoryEventCommand;
import seedu.cc.logic.parser.appointment.AddAppointmentCommandParser;
import seedu.cc.logic.parser.appointment.AddPrescriptionCommandParser;
import seedu.cc.logic.parser.appointment.DeleteAppointmentEventCommandParser;
import seedu.cc.logic.parser.appointment.DeletePrescriptionCommandParser;
import seedu.cc.logic.parser.appointment.EditAppointmentEventCommandParser;
import seedu.cc.logic.parser.appointment.EditPrescriptionCommandParser;
import seedu.cc.logic.parser.appointment.ListAppointmentEventsCommandParser;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.logic.parser.medicalhistory.AddMedicalHistoryEventCommandParser;
import seedu.cc.logic.parser.medicalhistory.DeleteMedicalHistoryEventCommandParser;
import seedu.cc.logic.parser.medicalhistory.EditMedicalHistoryEventCommandParser;
import seedu.cc.logic.parser.medicalhistory.ListMedicalHistoryEventCommandParser;

/**
 * Parses user input.
 */
public class ClinicBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(ClinicBookParser.class);

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

        // AppointmentEvent commands
        case AddAppointmentEventCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case ListAppointmentEventsCommand.COMMAND_WORD:
            return new ListAppointmentEventsCommandParser().parse(arguments);

        case EditAppointmentEventCommand.COMMAND_WORD:
            return new EditAppointmentEventCommandParser().parse(arguments);

        case DeleteAppointmentEventCommand.COMMAND_WORD:
            return new DeleteAppointmentEventCommandParser().parse(arguments);

        // Prescription commands
        case AddPrescriptionCommand.COMMAND_WORD:
            return new AddPrescriptionCommandParser().parse(arguments);

        case EditPrescriptionCommand.COMMAND_WORD:
            return new EditPrescriptionCommandParser().parse(arguments);

        case DeletePrescriptionCommand.COMMAND_WORD:
            return new DeletePrescriptionCommandParser().parse(arguments);

        // Medical History Event commands
        case AddMedicalHistoryEventCommand.COMMAND_WORD:
            return new AddMedicalHistoryEventCommandParser().parse(arguments);

        case ListMedicalHistoryEventCommand.COMMAND_WORD:
            return new ListMedicalHistoryEventCommandParser().parse(arguments);

        case EditMedicalHistoryEventCommand.COMMAND_WORD:
            return new EditMedicalHistoryEventCommandParser().parse(arguments);

        case DeleteMedicalHistoryEventCommand.COMMAND_WORD:
            return new DeleteMedicalHistoryEventCommandParser().parse(arguments);

        case SwitchCommand.COMMAND_WORD:
            return new SwitchCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
