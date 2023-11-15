package seedu.cc.logic.parser.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_APPT_DATE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_APPT_TIME;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_PATIENT_INDEX;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.appointmentcommands.EditAppointmentEventCommand;
import seedu.cc.logic.commands.appointmentcommands.EditAppointmentEventCommand.EditAppointmentEventDescriptor;
import seedu.cc.logic.parser.ArgumentMultimap;
import seedu.cc.logic.parser.ArgumentTokenizer;
import seedu.cc.logic.parser.Parser;
import seedu.cc.logic.parser.ParserUtil;
import seedu.cc.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAppointmentEventCommand object.
 */
public class EditAppointmentEventCommandParser implements Parser<EditAppointmentEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAppointmentEventCommand
     * and returns a EditAppointmentEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT_INDEX, PREFIX_APPT_DATE, PREFIX_APPT_TIME);

        Index eventIndex;

        try {
            eventIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentEventCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PATIENT_INDEX, PREFIX_APPT_DATE, PREFIX_APPT_TIME);

        Index patientIndex;

        if (argMultimap.getValue(PREFIX_PATIENT_INDEX).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentEventCommand.MESSAGE_USAGE));
        }

        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentEventCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentEventDescriptor editAppointmentEventDescriptor =
                new EditAppointmentEventDescriptor();


        if (argMultimap.getValue(PREFIX_APPT_DATE).isPresent()) {
            editAppointmentEventDescriptor.setDate(ParserUtil.parseDate(argMultimap
                    .getValue(PREFIX_APPT_DATE).get()));
        }

        if (argMultimap.getValue(PREFIX_APPT_TIME).isPresent()) {
            editAppointmentEventDescriptor.setTime(ParserUtil.parseTime(argMultimap
                    .getValue(PREFIX_APPT_TIME).get()));
        }


        return new EditAppointmentEventCommand(eventIndex, patientIndex, editAppointmentEventDescriptor);
    }

}
