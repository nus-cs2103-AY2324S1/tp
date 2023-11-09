package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class HelpCommandParserTest {
    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_emptyArgs_returnsHelpCommand() throws ParseException {
        // No arguments should return a HelpCommand
        HelpCommand expectedHelpCommand = new HelpCommand();
        assertParseSuccess(parser, "", expectedHelpCommand);
    }

    @Test
    public void parse_nonEmptyArgs_throwsParseException() {
        assertParseFailure(parser, "some random string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }
}
