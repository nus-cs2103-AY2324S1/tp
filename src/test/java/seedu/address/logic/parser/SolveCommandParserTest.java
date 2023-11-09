package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CARD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SolveCommand;



/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the PractiseCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the PractiseCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class SolveCommandParserTest {

    private SolveCommandParser parser = new SolveCommandParser();

    @Test
    public void parse_validArgs_returnsSolveCommand() {
        assertParseSuccess(parser, "1", new SolveCommand(INDEX_FIRST_CARD));
    }

    @Test
    public void parse_emptyArgs_returnsSolveCommand() {
        assertParseSuccess(parser, "", new SolveCommand(INDEX_FIRST_CARD));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SolveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SolveCommand.MESSAGE_USAGE));
    }
}
