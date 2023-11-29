package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteDentistCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteDentistCommandParserTest {
    private final DeleteDentistCommandParser parser = new DeleteDentistCommandParser();

    @Test
    public void parse_validDentistId_returnsDeleteDentistCommand() throws ParseException {
        long dentistId = 1;
        String args = "1";
        DeleteDentistCommand expectedCommand = new DeleteDentistCommand(dentistId);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_validDentistIdWithWhitespace_returnsDeleteDentistCommand() {
        long dentistId = 2;
        String args = "2";
        DeleteDentistCommand expectedCommand = new DeleteDentistCommand(dentistId);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_validDentistIdWithLeadingZero_returnsDeleteDentistCommand() {
        long dentistId = 3;
        String args = "03";
        DeleteDentistCommand expectedCommand = new DeleteDentistCommand(dentistId);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDentistCommand.MESSAGE_USAGE));
    }

}
