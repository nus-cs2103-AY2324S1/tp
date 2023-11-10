package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_DOCTOR_NRIC_DESC_CHERYL;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_DOCTOR_NRIC_DESC_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_PATIENT_NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_PATIENT_NRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_MALE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DOCTOR_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_PATIENT_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_1_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_DEREK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_IC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Ic;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Appointment expectedAppointment = new AppointmentBuilder(APPOINTMENT_1).build();

        assertParseSuccess(parser,
                APPOINTMENT_DOCTOR_NRIC_DESC_DEREK + APPOINTMENT_PATIENT_NRIC_DESC_BOB + VALID_DATE_1_DESC,
                new AddAppointmentCommand(expectedAppointment));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedAppointmentString =
                APPOINTMENT_DOCTOR_NRIC_DESC_DEREK + APPOINTMENT_PATIENT_NRIC_DESC_BOB + VALID_DATE_1_DESC;

        // multiple doctor ic
        assertParseFailure(parser, APPOINTMENT_DOCTOR_NRIC_DESC_CHERYL + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOCTOR_IC));

        // multiple patient ic
        assertParseFailure(parser, APPOINTMENT_PATIENT_NRIC_DESC_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PATIENT_IC));

        // multiple appointment time
        assertParseFailure(parser, VALID_DATE_2_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_TIME));


        // invalid value followed by valid value

        // invalid doctor ic
        assertParseFailure(parser, INVALID_APPOINTMENT_DOCTOR_NRIC_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOCTOR_IC));

        // invalid patient ic
        assertParseFailure(parser, INVALID_APPOINTMENT_PATIENT_NRIC_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PATIENT_IC));

        // invalid appointment time
        assertParseFailure(parser, INVALID_DATE_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_TIME));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);

        // missing doctor ic prefix
        assertParseFailure(parser, VALID_NRIC_DEREK + APPOINTMENT_PATIENT_NRIC_DESC_BOB + VALID_DATE_1_DESC,
                expectedMessage);

        // missing patient ic prefix
        assertParseFailure(parser, APPOINTMENT_DOCTOR_NRIC_DESC_DEREK + VALID_NRIC_BOB + VALID_DATE_1_DESC,
                expectedMessage);

        // missing appointment time prefix
        assertParseFailure(parser,
                APPOINTMENT_DOCTOR_NRIC_DESC_DEREK + APPOINTMENT_PATIENT_NRIC_DESC_BOB + VALID_DATE_1, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NRIC_DEREK + VALID_NRIC_BOB + VALID_DATE_1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid doctor ic
        assertParseFailure(parser,
                INVALID_APPOINTMENT_DOCTOR_NRIC_DESC + APPOINTMENT_PATIENT_NRIC_DESC_BOB + VALID_DATE_1_DESC,
                Ic.MESSAGE_CONSTRAINTS);

        // invalid patient ic
        assertParseFailure(parser,
                APPOINTMENT_DOCTOR_NRIC_DESC_DEREK + INVALID_APPOINTMENT_PATIENT_NRIC_DESC + VALID_DATE_1_DESC,
                Ic.MESSAGE_CONSTRAINTS);

        // invalid appointment time
        assertParseFailure(parser,
                APPOINTMENT_DOCTOR_NRIC_DESC_DEREK + APPOINTMENT_PATIENT_NRIC_DESC_BOB + INVALID_DATE_DESC,
                AppointmentTime.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                INVALID_APPOINTMENT_DOCTOR_NRIC_DESC + APPOINTMENT_PATIENT_NRIC_DESC_BOB + INVALID_DATE_DESC,
                Ic.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + NRIC_DESC_BOB + GENDER_DESC_MALE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
    }
}
