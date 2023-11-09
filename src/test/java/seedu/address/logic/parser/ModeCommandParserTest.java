package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ModeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ModeCommandParserTest {
    private ModeCommandParser parser = new ModeCommandParser();

    @Test
    public void parse_emptyArgs_returnsModeCommand() throws ParseException {
        // No arguments should return a ModeCommand
        ModeCommand expectedModeCommand = new ModeCommand();
        assertParseSuccess(parser, "", expectedModeCommand);
    }

    @Test
    public void parse_nonEmptyArgs_throwsParseException() {
        assertParseFailure(parser, "some random string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModeCommand.MESSAGE_USAGE));
    }
}
