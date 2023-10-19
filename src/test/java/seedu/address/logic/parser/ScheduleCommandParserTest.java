package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_FORMAT;
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
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.model.appointment.Appointment;

class ScheduleCommandParserTest {
    private ScheduleCommandParser parser = new ScheduleCommandParser();
    @Test
    public void parse_validScheduleInput_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " "
                + PREFIX_APPOINTMENT + VALID_APPOINTMENT_NAME + " "
                + PREFIX_APPOINTMENT_DATE + VALID_APPOINTMENT_DATE;

        LocalDateTime date = Appointment.parseAppointmentDate(VALID_APPOINTMENT_DATE);
        Appointment appointment = new Appointment(VALID_APPOINTMENT_NAME, date);

        assertParseSuccess(parser, userInput, new ScheduleCommand(targetIndex, appointment));
    }

    @Test
    public void parse_invalidDate_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String invalidTimeString = targetIndex.getOneBased() + " "
                + PREFIX_APPOINTMENT + VALID_APPOINTMENT_NAME + " "
                + PREFIX_APPOINTMENT_DATE + INVALID_APPOINTMENT_TIME_FORMAT;

        assertParseFailure(parser, invalidTimeString, Appointment.MESSAGE_CONSTRAINTS);

        String invalidDateString = targetIndex.getOneBased() + " "
                + PREFIX_APPOINTMENT + VALID_APPOINTMENT_NAME + " "
                + PREFIX_APPOINTMENT_DATE + INVALID_APPOINTMENT_DATE_FORMAT;

        assertParseFailure(parser, invalidDateString, Appointment.MESSAGE_CONSTRAINTS);
    }
}
