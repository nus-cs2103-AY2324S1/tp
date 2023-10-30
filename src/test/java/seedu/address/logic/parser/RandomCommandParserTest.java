package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_NUMBER_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RandomCommand;

/**
 * Tests MarkPresentCommandParser.
 */
public class RandomCommandParserTest {
    private RandomCommandParser parser = new RandomCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RandomCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RandomCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsMarkCommand() {
        int i = 1;
        assertParseSuccess(parser, " " + i, new RandomCommand(Index.fromOneBased(i)));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, STUDENT_NUMBER_DESC_BOB, ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
