package seedu.address.logic.parser.appointmentparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_SORT_ATTRIBUTE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommands.SortCommand;

public class SortCommandParserTest {

    private static final String INVALID_PREAMBLE_1 = " descending";
    private static final String INVALID_ATTRIBUTE_DESC = " by= friend";
    private static final String VALID_PREAMBLE_1 = " desc";
    private static final String VALID_ATTRIBUTE_DESC = " by= time";
    private static final String VALID_ATTRIBUTE_1 = "time";
    private SortCommandParser parser = new SortCommandParser();
    @Test
    public void parse_invalidPreamble_failure() {
        assertParseFailure(parser, INVALID_PREAMBLE_1 + VALID_ATTRIBUTE_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_invalidAttribute_failure() {
        assertParseFailure(parser, VALID_PREAMBLE_1 + INVALID_ATTRIBUTE_DESC,
                String.format(MESSAGE_INVALID_SORT_ATTRIBUTE, SortCommandParser.printAttributes()));
    }

    @Test
    public void parse_validPreambleAndAttribute_success() {
        SortCommand expectedSortCommand = new SortCommand(false, VALID_ATTRIBUTE_1);
        assertParseSuccess(parser, VALID_PREAMBLE_1 + VALID_ATTRIBUTE_DESC, expectedSortCommand);
    }
}
