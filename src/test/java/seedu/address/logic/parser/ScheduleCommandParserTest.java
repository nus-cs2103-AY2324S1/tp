package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_TIME_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.model.appointment.Appointment;

class ScheduleCommandParserTest {
    private ScheduleCommandParser parser = new ScheduleCommandParser();
    private Index targetIndex = INDEX_FIRST_PERSON;

    @Test
    public void parse_validScheduleInput_success() {
        String userInput = targetIndex.getOneBased() + APPOINTMENT_NAME_DESC + APPOINTMENT_DATE_DESC;

        LocalDateTime date = Appointment.parseAppointmentDate(VALID_APPOINTMENT_DATE);
        Appointment appointment = new Appointment(VALID_APPOINTMENT_NAME, date);

        assertParseSuccess(parser, userInput, new ScheduleCommand(targetIndex, appointment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE);

        //missing appointment name prefix
        assertParseFailure(parser, targetIndex.getOneBased() + " " + VALID_APPOINTMENT_NAME
                + APPOINTMENT_DATE_DESC, expectedMessage);

        //missing appointment date prefix
        assertParseFailure(parser, targetIndex.getOneBased() + APPOINTMENT_NAME_DESC
                + " " + VALID_APPOINTMENT_DATE, expectedMessage);

        //missing index
        assertParseFailure(parser, APPOINTMENT_NAME_DESC + APPOINTMENT_DATE_DESC, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid appointment name
        String invalidNameString = targetIndex.getOneBased() + INVALID_APPOINTMENT_NAME_DESC
                + APPOINTMENT_DATE_DESC;
        assertParseFailure(parser, invalidNameString, Appointment.MESSAGE_DESC_CONSTRAINTS);

        // invalid time format
        String invalidTimeFormatString = targetIndex.getOneBased() + APPOINTMENT_NAME_DESC
                + INVALID_APPOINTMENT_TIME_FORMAT;
        assertParseFailure(parser, invalidTimeFormatString, Appointment.MESSAGE_DATE_CONSTRAINTS);

        // invalid date format
        String invalidDateFormatString = targetIndex.getOneBased() + APPOINTMENT_NAME_DESC
                + INVALID_APPOINTMENT_DATE_FORMAT;
        assertParseFailure(parser, invalidDateFormatString, Appointment.MESSAGE_DATE_CONSTRAINTS);

        // invalid date
        String invalidDateString = targetIndex.getOneBased() + APPOINTMENT_NAME_DESC + INVALID_APPOINTMENT_DATE;
        assertParseFailure(parser, invalidDateString, Appointment.MESSAGE_INVALID_DATE);

        // duplicate name
        String validExpectedSchedule = APPOINTMENT_NAME_DESC + APPOINTMENT_DATE_DESC;
        assertParseFailure(parser, targetIndex.getOneBased() + " " + PREFIX_APPOINTMENT
                + VALID_APPOINTMENT_NAME + validExpectedSchedule,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT));

        // duplicate date
        assertParseFailure(parser, targetIndex.getOneBased() + validExpectedSchedule + " "
                + PREFIX_APPOINTMENT_DATE + VALID_APPOINTMENT_DATE,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_DATE));
    }
}
