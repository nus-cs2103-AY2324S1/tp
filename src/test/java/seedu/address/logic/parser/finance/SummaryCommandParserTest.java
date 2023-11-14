package seedu.address.logic.parser.finance;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.finance.SummaryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.ClientNameExactMatchPredicate;
class SummaryCommandParserTest {
    private SummaryCommandParser parser = new SummaryCommandParser();

    @Test
    public void parse_validArgs_returnsSummaryCommand() throws ParseException {
        String args = "Alice";
        SummaryCommand expectedCommand = new SummaryCommand(
                new ClientNameExactMatchPredicate(args));
        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String args = "";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SummaryCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }

    @Test
    public void parse_whitespaceArgs_throwsParseException() {
        String args = "    ";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SummaryCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }
}

