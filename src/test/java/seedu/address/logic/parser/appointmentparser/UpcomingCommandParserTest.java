package seedu.address.logic.parser.appointmentparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommands.UpcomingCommand;

public class UpcomingCommandParserTest {
    private UpcomingCommandParser parser = new UpcomingCommandParser();

    @Test
    public void parse_validArgs_returnsAppointmentUpcomingCommand() {
        // no leading and trailing whitespaces
        UpcomingCommand expectedUpcomingCommand = new UpcomingCommand();
        // multiple whitespaces between keywords
        assertParseSuccess(parser, "",
                expectedUpcomingCommand);
    }

    @Test
    public void parse_nonEmptyString_throwsParseException() {
        assertParseFailure(parser, "some random string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpcomingCommand.MESSAGE_USAGE));
    }
}
