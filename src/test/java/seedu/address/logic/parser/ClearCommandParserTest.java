package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ClearCommandParserTest {
    private ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_emptyArgs_returnsClearCommand() throws ParseException {
        // No arguments should return a ClearCommand
        ClearCommand expectedClearCommand = new ClearCommand();
        assertParseSuccess(parser, "", expectedClearCommand);
    }

    @Test
    public void parse_nonEmptyArgs_throwsParseException() {
        assertParseFailure(parser, "some random string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
    }
}
