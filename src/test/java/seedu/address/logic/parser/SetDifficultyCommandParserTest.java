package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetDifficultyCommand;


/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the PractiseCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the PractiseCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class SetDifficultyCommandParserTest {

    private SetDifficultyCommandParser parser = new SetDifficultyCommandParser();

    @Test
    public void parse_validArgs_returnsSetDifficultyCommand() {
        assertParseSuccess(parser, "1 d/ easy", new SetDifficultyCommand(Index.fromZeroBased(0), "easy"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "invalid argument",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDifficultyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noIndex_throwsParseException() {
        assertParseSuccess(parser, " d/ easy", new SetDifficultyCommand(Index.fromZeroBased(0), "easy"));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "-1 d/ easy", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetDifficultyCommand.MESSAGE_USAGE));
    }

}
