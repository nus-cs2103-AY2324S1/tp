package seedu.address.logic.parser;

import static org.mockito.Mockito.mock;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.CalendarWindow;

public class DeleteAppointmentCommandParserTest {

    private final DeleteAppointmentCommandParser parser = new DeleteAppointmentCommandParser();

    @Test
    public void parse_validAppointmentId_returnsDeleteAppointmentCommand() throws ParseException {
        long appointmentId = 1;
        String args = "1";
        DeleteAppointmentCommand expectedCommand = new DeleteAppointmentCommand(appointmentId);

        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        expectedCommand.setCalendarWindow(mockCalendarWindow);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_validAppointmentIdWithWhitespace_returnsDeleteAppointmentCommand() {
        long appointmentId = 2;
        String args = "2";
        DeleteAppointmentCommand expectedCommand = new DeleteAppointmentCommand(appointmentId);

        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        expectedCommand.setCalendarWindow(mockCalendarWindow);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_validAppointmentIdWithLeadingZero_returnsDeleteAppointmentCommand() {
        long appointmentId = 3;
        String args = "03";
        DeleteAppointmentCommand expectedCommand = new DeleteAppointmentCommand(appointmentId);

        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        expectedCommand.setCalendarWindow(mockCalendarWindow);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAppointmentCommand.MESSAGE_USAGE));
    }
}
