package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentIcPredicate;

public class FindAppointmentCommandParserTest {
    private FindAppointmentCommandParser parser = new FindAppointmentCommandParser();

    private String testInput = "T1111111G";

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() throws ParseException {
        // no leading and trailing whitespaces
        FindAppointmentCommand expectedFindAppointmentCommand =
                new FindAppointmentCommand(new AppointmentIcPredicate(testInput));
        assertParseSuccess(parser, "T1111111G", expectedFindAppointmentCommand);
    }
}
