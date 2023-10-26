package seedu.address.logic.parser.appointmentparser;

import seedu.address.logic.commands.appointmentcommands.FindPatientAppointmentCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.appointmentfilters.FindPatientFilter;

import java.util.Arrays;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FindPatientAppointmentCommandParser implements Parser<FindPatientAppointmentCommand> {
    /**
     * Parses {@code userInput} and returns a FindPatientAppointmentCommand object to be executed.
     *
     * @param userInput user's input which is of type String.
     * @throws ParseException if {@code userInput} does not conform to the expected format.
     */
    @Override
    public FindPatientAppointmentCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientAppointmentCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindPatientAppointmentCommand(new FindPatientFilter(Arrays.asList(nameKeywords)));
    }
}
