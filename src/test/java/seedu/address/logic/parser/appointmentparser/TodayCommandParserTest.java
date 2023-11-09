package seedu.address.logic.parser.appointmentparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommands.TodayCommand;

public class TodayCommandParserTest {

    private TodayCommandParser parser = new TodayCommandParser();

    @Test
    public void parse_validArgs_returnsAppointmentTodayCommand() {
        // no leading and trailing whitespaces
        TodayCommand expectedTodayCommand = new TodayCommand();
        // multiple whitespaces between keywords
        assertParseSuccess(parser, "",
                expectedTodayCommand);
    }

    @Test
    public void parse_nonEmptyString_throwsParseException() {
        assertParseFailure(parser, "some random string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodayCommand.MESSAGE_USAGE));
    }
}
