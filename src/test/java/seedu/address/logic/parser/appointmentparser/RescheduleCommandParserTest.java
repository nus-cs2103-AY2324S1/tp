package seedu.address.logic.parser.appointmentparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.END_DESC_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.INVALID_END_DESC;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.INVALID_START_DESC;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.START_DESC_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_END_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_START_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointmentcommands.RescheduleCommand;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.testutil.AppointmentTimeBuilder;

public class RescheduleCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RescheduleCommand.MESSAGE_USAGE);
    private RescheduleCommandParser parser = new RescheduleCommandParser();
    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, START_DESC_ONE + END_DESC_ONE, MESSAGE_INVALID_FORMAT);

        // no start time specified
        assertParseFailure(parser, "1" + START_DESC_ONE, MESSAGE_INVALID_FORMAT);

        // no end time specified
        assertParseFailure(parser, "1" + END_DESC_ONE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-12" + START_DESC_ONE + END_DESC_ONE, MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + START_DESC_ONE + END_DESC_ONE, MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 random invalid argument", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 st/string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid start time
        assertParseFailure(parser, "1" + INVALID_START_DESC + END_DESC_ONE, AppointmentTime.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, "1" + START_DESC_ONE + INVALID_END_DESC, AppointmentTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + START_DESC_ONE + END_DESC_ONE;
        AppointmentTime appointmentTime = new AppointmentTimeBuilder()
                .withStart(VALID_START_ONE)
                .withEnd(VALID_END_ONE).build();
        RescheduleCommand expectedCommand = new RescheduleCommand(targetIndex, appointmentTime);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
