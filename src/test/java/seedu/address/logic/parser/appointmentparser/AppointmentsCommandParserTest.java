package seedu.address.logic.parser.appointmentparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommands.AppointmentsCommand;

public class AppointmentsCommandParserTest {
    private AppointmentsCommandParser parser = new AppointmentsCommandParser();

    @Test
    public void parse_validArgs_returnsAppointmentsCommand() {
        AppointmentsCommand expectedAppointmentsCommand = new AppointmentsCommand();
        assertParseSuccess(parser, "", expectedAppointmentsCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Any arguments should result in a ParseException
        assertParseFailure(parser, "some random string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppointmentsCommand.MESSAGE_USAGE));
    }
}
