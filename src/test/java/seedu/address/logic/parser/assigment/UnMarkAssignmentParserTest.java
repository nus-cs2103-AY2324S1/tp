package seedu.address.logic.parser.assigment;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.assignment.UnMarkAssignmentCommand;
import seedu.address.logic.parser.assignment.UnMarkAssignmentParser;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the UnMarkAssignmentCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the UnMarkAssignmentCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class UnMarkAssignmentParserTest {
    private UnMarkAssignmentParser parser = new UnMarkAssignmentParser();

    @Test
    public void parse_validArgs_returnsMarkCommand() {
        assertParseSuccess(parser, "1", new UnMarkAssignmentCommand(INDEX_FIRST_IN_LIST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_INDEX));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnMarkAssignmentCommand.MESSAGE_USAGE));
    }
}
