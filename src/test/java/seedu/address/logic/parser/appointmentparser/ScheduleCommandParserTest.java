package seedu.address.logic.parser.appointmentparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.DESCRIPTION_DESC_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.END_DESC_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.INVALID_END_DESC;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.INVALID_PATIENT_DESC;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.INVALID_PRIORITY_TAG_DESC;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.INVALID_START_DESC;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.PATIENT_ONE_DESC;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.PRIORITY_TAG_DESC_HIGH;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.PRIORITY_TAG_DESC_LOW;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.START_DESC_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_DESCRIPTION_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_END_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_PATIENT_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_PRIORITY_TAG_HIGH;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_START_ONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.appointmentcommands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDescription;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Name;
import seedu.address.model.tag.PriorityTag;
import seedu.address.testutil.AppointmentTimeBuilder;

public class ScheduleCommandParserTest {
    private ScheduleCommandParser parser = new ScheduleCommandParser();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {

        String patient = VALID_PATIENT_ONE;
        Name patientName = new Name(patient);
        String userInput = PATIENT_ONE_DESC + START_DESC_ONE + END_DESC_ONE + DESCRIPTION_DESC_ONE
                + PRIORITY_TAG_DESC_HIGH;
        AppointmentTime appointmentTime = new AppointmentTimeBuilder()
                .withStart(VALID_START_ONE)
                .withEnd(VALID_END_ONE).build();
        String appointmentDescription = VALID_DESCRIPTION_ONE;
        AppointmentDescription appointmentDescription1 = new AppointmentDescription(appointmentDescription);
        String priority = VALID_PRIORITY_TAG_HIGH;
        PriorityTag priorityTag = new PriorityTag(priority);

        Appointment expectedAppointment = new Appointment(patientName, appointmentTime, appointmentDescription1,
                priorityTag);

        ScheduleCommand expectedCommand = new ScheduleCommand(expectedAppointment, patientName);

        assertEquals(parser.parse(userInput).toString(), expectedCommand.toString());
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE);

        // missing patient prefix
        assertParseFailure(parser, START_DESC_ONE + END_DESC_ONE + DESCRIPTION_DESC_ONE
                        + PRIORITY_TAG_DESC_HIGH,
                expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, PATIENT_ONE_DESC + END_DESC_ONE + DESCRIPTION_DESC_ONE
                + PRIORITY_TAG_DESC_HIGH,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, PATIENT_ONE_DESC + START_DESC_ONE + DESCRIPTION_DESC_ONE
                        + PRIORITY_TAG_DESC_HIGH,
                expectedMessage);

        // missing appointment description prefix
        assertParseFailure(parser, PATIENT_ONE_DESC + START_DESC_ONE + END_DESC_ONE
                        + PRIORITY_TAG_DESC_HIGH,
                expectedMessage);

        // missing appointment priority tag prefix
        assertParseFailure(parser, PATIENT_ONE_DESC + START_DESC_ONE + END_DESC_ONE + DESCRIPTION_DESC_ONE,
                expectedMessage);

        // missing all inputs
        assertParseFailure(parser, "test",
                expectedMessage);
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedAppointmentString = PATIENT_ONE_DESC + START_DESC_ONE + END_DESC_ONE + DESCRIPTION_DESC_ONE
                + PRIORITY_TAG_DESC_HIGH;

        // multiple names
        assertParseFailure(parser, PATIENT_ONE_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_PATIENT));

        // multiple start times
        assertParseFailure(parser, START_DESC_ONE + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_START));

        // multiple end times
        assertParseFailure(parser, END_DESC_ONE + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_END));

        // multiple appointment descriptions
        assertParseFailure(parser, DESCRIPTION_DESC_ONE + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_DESCRIPTION));

        // multiple priority tags
        assertParseFailure(parser, PRIORITY_TAG_DESC_LOW + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_PRIORITY));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedAppointmentString + PATIENT_ONE_DESC + START_DESC_ONE + END_DESC_ONE
                        + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_PATIENT, PREFIX_APPOINTMENT_START,
                        PREFIX_APPOINTMENT_END, PREFIX_APPOINTMENT_DESCRIPTION, PREFIX_APPOINTMENT_PRIORITY));

        // invalid value followed by valid value

        // invalid patient name
        assertParseFailure(parser, INVALID_PATIENT_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_PATIENT));

        // invalid start time
        assertParseFailure(parser, INVALID_START_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_START));

        // invalid end time
        assertParseFailure(parser, INVALID_END_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_END));

        // invalid appointment description
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_DESCRIPTION));

        // invalid address
        assertParseFailure(parser, INVALID_PRIORITY_TAG_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_PRIORITY));

        // valid value followed by invalid value

        // invalid patient name
        assertParseFailure(parser, validExpectedAppointmentString + INVALID_PATIENT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_PATIENT));

        // invalid start time
        assertParseFailure(parser, validExpectedAppointmentString + INVALID_START_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_START));

        // invalid end time
        assertParseFailure(parser, validExpectedAppointmentString + INVALID_END_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_END));

        // invalid appointment description
        assertParseFailure(parser, validExpectedAppointmentString + INVALID_DESCRIPTION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_DESCRIPTION));

        // invalid address
        assertParseFailure(parser, validExpectedAppointmentString + INVALID_PRIORITY_TAG_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_PRIORITY));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid patient name
        assertParseFailure(parser, INVALID_PATIENT_DESC + START_DESC_ONE + END_DESC_ONE + DESCRIPTION_DESC_ONE
                + PRIORITY_TAG_DESC_HIGH, Name.MESSAGE_CONSTRAINTS);

        // invalid start time
        assertParseFailure(parser, PATIENT_ONE_DESC + INVALID_START_DESC + END_DESC_ONE + DESCRIPTION_DESC_ONE
                + PRIORITY_TAG_DESC_HIGH, AppointmentTime.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, PATIENT_ONE_DESC + START_DESC_ONE + INVALID_END_DESC + DESCRIPTION_DESC_ONE
                + PRIORITY_TAG_DESC_HIGH, AppointmentTime.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, PATIENT_ONE_DESC + START_DESC_ONE + END_DESC_ONE + INVALID_DESCRIPTION_DESC
                + PRIORITY_TAG_DESC_HIGH, AppointmentDescription.MESSAGE_CONSTRAINTS);

        // invalid priority tag
        assertParseFailure(parser, PATIENT_ONE_DESC + START_DESC_ONE + END_DESC_ONE + DESCRIPTION_DESC_ONE
                + INVALID_PRIORITY_TAG_DESC, PriorityTag.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PATIENT_ONE_DESC + START_DESC_ONE + END_DESC_ONE + DESCRIPTION_DESC_ONE
                        + PRIORITY_TAG_DESC_HIGH,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
    }
}
