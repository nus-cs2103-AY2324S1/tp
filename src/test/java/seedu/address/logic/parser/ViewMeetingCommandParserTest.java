package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewMeetingCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path
 * variations outside of the ViewMeetingCommand code. For example, inputs "1"
 * and "1 abc" take the same path through the ViewMeetingCommand, and therefore
 * we test only one of them. The path variation for those two cases occur inside
 * the ParserUtil, and therefore should be covered by the ParserUtilTest.
 */
public class ViewMeetingCommandParserTest {

    private ViewMeetingCommandParser parser = new ViewMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsViewMeetingCommand() {
        assertParseSuccess(parser, "1", new ViewMeetingCommand(INDEX_FIRST_MEETING));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // incorrect index format
        assertParseFailure(parser, "a", MESSAGE_INVALID_INDEX_FORMAT + "\n" + ViewMeetingCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, "", MESSAGE_MISSING_INDEX + "\n" + ViewMeetingCommand.MESSAGE_USAGE);
    }
}
