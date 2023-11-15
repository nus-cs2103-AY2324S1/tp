package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ListAllConflictsCommand;


public class ListAllConflictsCommandParserTest {
    private ListAllConflictsCommandParser parser = new ListAllConflictsCommandParser();

    @Test
    public void parse_emptyPreamble_success() {
        assertParseSuccess(parser, "", new ListAllConflictsCommand());
    }

    @Test
    public void parse_preambleWhitespace_success() {
        assertParseSuccess(parser, " ", new ListAllConflictsCommand());
    }

    @Test
    public void parse_extraValues_failure() {
        // Random values
        assertParseFailure(parser, "ABCDEFGH",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListAllConflictsCommand.MESSAGE_USAGE));

        // Random prefixes
        assertParseFailure(parser, "mn/ABCD d/2",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListAllConflictsCommand.MESSAGE_USAGE));
    }
}
