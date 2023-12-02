package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.SortOrder;
import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validAscendingInputs_success() {
        // various valid ways to denote ascending
        assertParseSuccess(parser, "a", new SortCommand(SortOrder.ASCENDING));
        assertParseSuccess(parser, "asc", new SortCommand(SortOrder.ASCENDING));
        assertParseSuccess(parser, "ascending", new SortCommand(SortOrder.ASCENDING));
    }

    @Test
    public void parse_validDescendingInputs_success() {
        // various valid ways to denote descending
        assertParseSuccess(parser, "d", new SortCommand(SortOrder.DESCENDING));
        assertParseSuccess(parser, "desc", new SortCommand(SortOrder.DESCENDING));
        assertParseSuccess(parser, "descending", new SortCommand(SortOrder.DESCENDING));
    }

    @Test
    public void parse_defaultBehavior_success() {
        // default to ascending when no order is specified
        assertParseSuccess(parser, "", new SortCommand(SortOrder.ASCENDING));
    }

    @Test
    public void parse_invalidInputs_throwsParseException() {
        // invalid sort order
        assertParseFailure(parser, "invalidOrder",
                String.format(SortCommand.MESSAGE_INVALID_SORT_ORDER, SortCommand.MESSAGE_USAGE));
    }
}
