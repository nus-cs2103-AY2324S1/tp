package seedu.address.logic.parser.appointmentparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommands.FindAppointmentCommand;
import seedu.address.model.appointment.PatientContainsKeywordsPredicate;

public class FindAppointmentParserTest {
    private FindAppointmentCommandParser parser = new FindAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindAppointmentCommand() {
        // no leading and trailing whitespaces
        FindAppointmentCommand expectedFindAppointmentCommand =
                new FindAppointmentCommand(new PatientContainsKeywordsPredicate(Arrays.asList("Adam", "Ben")));
        assertParseSuccess(parser, "Adam Ben", expectedFindAppointmentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Adam \n \t Ben  \t", expectedFindAppointmentCommand);
    }
}
