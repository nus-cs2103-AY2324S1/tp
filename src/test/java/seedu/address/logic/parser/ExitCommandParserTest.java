package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ExitCommand;

public class ExitCommandParserTest {
    private ExitCommandParser parser = new ExitCommandParser();

    @Test
    public void parse_emptyPreamble_success() {
        assertParseSuccess(parser, "", new ExitCommand());
    }

    @Test
    public void parse_preambleWhitespace_success() {
        assertParseSuccess(parser, " ", new ExitCommand());
    }

    @Test
    public void parse_extraValues_failure() {
        // Random values
        assertParseFailure(parser, "ABCDEFGH",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ExitCommand.MESSAGE_USAGE));

        // Random prefixes
        assertParseFailure(parser, "mn/ABCD d/2",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ExitCommand.MESSAGE_USAGE));
    }
}
