package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INPUT_DENTIST_ID_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.INPUT_INVALID_DENTIST_ID_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.INPUT_INVALID_PATIENT_ID_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.INPUT_INVALID_START_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.INPUT_PATIENT_ID_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.INPUT_START_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.INPUT_TREATMENT_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.model.appointments.Appointment;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandParserTest {

    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Appointment expectedAppointment = new AppointmentBuilder().parserBuild();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INPUT_DENTIST_ID_APPOINTMENT
                        + INPUT_PATIENT_ID_APPOINTMENT + INPUT_START_APPOINTMENT + INPUT_TREATMENT_APPOINTMENT,
                new AddAppointmentCommand(expectedAppointment));
    }

    @Test
    public void parse_fieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);

        // missing dentist id
        assertParseFailure(parser, INPUT_PATIENT_ID_APPOINTMENT + INPUT_START_APPOINTMENT
                + INPUT_TREATMENT_APPOINTMENT, expectedMessage);

        // missing patient id
        assertParseFailure(parser, INPUT_DENTIST_ID_APPOINTMENT + INPUT_START_APPOINTMENT
                + INPUT_TREATMENT_APPOINTMENT, expectedMessage);

        // missing appointment start time
        assertParseFailure(parser, INPUT_DENTIST_ID_APPOINTMENT + INPUT_PATIENT_ID_APPOINTMENT
                + INPUT_TREATMENT_APPOINTMENT, expectedMessage);

        // missing treatment
        assertParseFailure(parser, INPUT_DENTIST_ID_APPOINTMENT
                + INPUT_PATIENT_ID_APPOINTMENT + INPUT_START_APPOINTMENT, expectedMessage);
    }

    @Test
    public void parse_invalidId_throwsParseException() {
        String invalidDentistId = "Invalid input. Please enter a valid integer value for dentist ID.";

        // invalid dentist id
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INPUT_INVALID_DENTIST_ID_APPOINTMENT
                + INPUT_PATIENT_ID_APPOINTMENT + INPUT_START_APPOINTMENT + INPUT_TREATMENT_APPOINTMENT,
                invalidDentistId);

        String invalidPatientId = "Invalid input. Please enter a valid integer value for patient ID.";

        // invalid patient id
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INPUT_DENTIST_ID_APPOINTMENT
                        + INPUT_INVALID_PATIENT_ID_APPOINTMENT + INPUT_START_APPOINTMENT + INPUT_TREATMENT_APPOINTMENT,
                invalidPatientId);

        String invalidStart = "Please enter start time in correct format: yyyy-MM-dd HH:mm";

        assertParseFailure(parser, PREAMBLE_WHITESPACE + INPUT_DENTIST_ID_APPOINTMENT
                        + INPUT_PATIENT_ID_APPOINTMENT + INPUT_INVALID_START_APPOINTMENT + INPUT_TREATMENT_APPOINTMENT,
                invalidStart);
    }
}
