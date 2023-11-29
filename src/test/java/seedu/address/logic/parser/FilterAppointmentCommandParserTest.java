package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FilterAppointmentCommandParserTest {

    private FilterAppointmentCommandParser parser = new FilterAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validDentist_returnsFilterAppointmentCommand() {
        FilterAppointmentCommand expectedCommand = new FilterAppointmentCommand("dentist", 1);

        assertParseSuccess(parser, "dentist 1", expectedCommand);
        assertParseSuccess(parser, " \n dentist \n \t 1  \t", expectedCommand);
    }

    @Test
    public void parse_validPatient_returnsFilterAppointmentCommand() {
        FilterAppointmentCommand expectedCommand = new FilterAppointmentCommand("patient", 1);

        assertParseSuccess(parser, "patient 1", expectedCommand);
        assertParseSuccess(parser, " \n patient \n \t 1  \t", expectedCommand);
    }

    @Test
    public void parse_invalidFormat() {
        String args = "";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidInputs() {
        String args = "dentist hello";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
