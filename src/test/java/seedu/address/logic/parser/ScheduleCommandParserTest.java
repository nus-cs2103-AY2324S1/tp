package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Time;
import seedu.address.model.appointment.exceptions.InvalidStartEndTimeException;
import seedu.address.model.student.Name;
import seedu.address.testutil.AppointmentBuilder;

public class ScheduleCommandParserTest {
    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws InvalidStartEndTimeException {
        Appointment expectedAppointment = new AppointmentBuilder().withName(VALID_NAME_BOB)
                .withDate(VALID_DATE_BOB).withStartTime("12:00").withEndTime(VALID_END_TIME_BOB)
                .withDescription(VALID_DESCRIPTION_BOB).build();

        // Whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + DATE_DESC_BOB
                        + START_TIME_DESC_BOB + END_TIME_DESC_BOB + DESCRIPTION_DESC_BOB,
                new ScheduleCommand(expectedAppointment));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedAppointmentString = NAME_DESC_BOB + DATE_DESC_BOB + START_TIME_DESC_BOB
                + END_TIME_DESC_BOB + DESCRIPTION_DESC_BOB;

        // Multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // Multiple dates
        assertParseFailure(parser, DATE_DESC_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // Multiple start times
        assertParseFailure(parser, START_TIME_DESC_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // Multiple end times
        assertParseFailure(parser, END_TIME_DESC_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_TIME));

        // Multiple descriptions
        assertParseFailure(parser, DESCRIPTION_DESC_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // Multiple fields repeated
        assertParseFailure(parser, validExpectedAppointmentString + NAME_DESC_AMY + DATE_DESC_AMY + START_TIME_DESC_AMY
                        + END_TIME_DESC_AMY + DESCRIPTION_DESC_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_DATE, PREFIX_START_TIME,
                        PREFIX_END_TIME, PREFIX_DESCRIPTION));

        // Invalid value followed by valid value

        // Invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // Invalid date
        assertParseFailure(parser, INVALID_DATE_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // Invalid start time
        assertParseFailure(parser, INVALID_START_TIME_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // Invalid end time
        assertParseFailure(parser, INVALID_END_TIME_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_TIME));

        // Invalid description
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE);

        // Missing name prefix
        assertParseFailure(parser, VALID_DATE_BOB + START_TIME_DESC_BOB + END_TIME_DESC_BOB + DESCRIPTION_DESC_BOB,
                expectedMessage);

        // Missing date prefix
        assertParseFailure(parser, NAME_DESC_BOB + START_TIME_DESC_BOB + END_TIME_DESC_BOB + DESCRIPTION_DESC_BOB,
                expectedMessage);

        // Missing start time prefix
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + END_TIME_DESC_BOB + DESCRIPTION_DESC_BOB,
                expectedMessage);

        // Missing end time prefix
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + START_TIME_DESC_BOB + DESCRIPTION_DESC_BOB,
                expectedMessage);

        // Missing description prefix
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + START_TIME_DESC_BOB + END_TIME_DESC_BOB,
                expectedMessage);

        // All prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_DATE_BOB + VALID_START_TIME_BOB
                + VALID_END_TIME_BOB + VALID_DESCRIPTION_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DATE_DESC_BOB + START_TIME_DESC_BOB
                + END_TIME_DESC_BOB + DESCRIPTION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // Invalid date
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_DATE_DESC + START_TIME_DESC_BOB
                + END_TIME_DESC_BOB + DESCRIPTION_DESC_BOB, Date.MESSAGE_CONSTRAINTS);

        // Invalid start time
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + INVALID_START_TIME_DESC
                + END_TIME_DESC_BOB + DESCRIPTION_DESC_BOB, Time.MESSAGE_CONSTRAINTS);

        // Invalid end time
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + START_TIME_DESC_BOB
                + INVALID_END_TIME_DESC + DESCRIPTION_DESC_BOB, Time.MESSAGE_CONSTRAINTS);

        // Invalid description
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_BOB + START_TIME_DESC_BOB
                + END_TIME_DESC_BOB + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);

        // Two invalid values, only the first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_DATE_DESC + START_TIME_DESC_BOB
                + END_TIME_DESC_BOB + DESCRIPTION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // Non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + DATE_DESC_BOB + START_TIME_DESC_BOB
                        + END_TIME_DESC_BOB + DESCRIPTION_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
    }
}
