package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeletePatientCommandParserTest {
    private final DeletePatientCommandParser parser = new DeletePatientCommandParser();

    @Test
    public void parse_validPatientId_returnsDeletePatientCommand() throws ParseException {
        long patientId = 1;
        String args = "1";
        DeletePatientCommand expectedCommand = new DeletePatientCommand(patientId);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_validPatientIdWithWhitespace_returnsDeletePatientCommand() {
        long patientId = 2;
        String args = "2";
        DeletePatientCommand expectedCommand = new DeletePatientCommand(patientId);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_validPatientIdWithLeadingZero_returnsDeletePatientCommand() {
        long patientId = 3;
        String args = "03";
        DeletePatientCommand expectedCommand = new DeletePatientCommand(patientId);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePatientCommand.MESSAGE_USAGE));
    }

}
