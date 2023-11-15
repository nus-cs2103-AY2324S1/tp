package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListConflictsCommand;


public class ListConflictsCommandParserTest {
    private ListConflictsCommandParser parser = new ListConflictsCommandParser();

    //write some test cases for the parser
    @Test
    public void parse_validArgs_success() {
        // Valid index
        assertParseSuccess(parser, "1", new ListConflictsCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_zeroIndex_failure() {
        // Valid index
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListConflictsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeIndex_failure() {
        // Valid index
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListConflictsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonNumberIndex_failure() {
        // Valid index
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListConflictsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_float_failure() {
        // Valid index
        assertParseFailure(parser, "1.0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListConflictsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_fraction_failure() {
        // Valid index
        assertParseFailure(parser, "1/2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListConflictsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_leadingZeroes_success() {
        // Valid index
        assertParseSuccess(parser, "001", new ListConflictsCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_multipleNumbers_failure() {
        // Valid index
        assertParseFailure(parser, "1 2 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListConflictsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPreamble_failure() {
        // No index specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListConflictsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_whitespacePreamble_failure() {
        // No index specified
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListConflictsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraValues_failure() {
        // Random values
        assertParseFailure(parser, "ABCDEFGH",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ListConflictsCommand.MESSAGE_USAGE));

        // Random prefixes
        assertParseFailure(parser, "mn/ABCD d/2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ListConflictsCommand.MESSAGE_USAGE));
    }
}
