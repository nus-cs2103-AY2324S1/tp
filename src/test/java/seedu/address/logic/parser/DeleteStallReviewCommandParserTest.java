package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteStallReviewCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteStallReviewCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteStallReviewCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteStallReviewCommandParserTest {

    private DeleteStallReviewCommandParser parser = new DeleteStallReviewCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteStallReviewCommand() {
        String userInput = PREAMBLE_WHITESPACE + " " + PREFIX_STALL + "1";
        assertParseSuccess(parser, userInput, new DeleteStallReviewCommand(INDEX_FIRST_STALL));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String userInput = PREAMBLE_WHITESPACE + " " + PREFIX_STALL + "a";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_STALL_DISPLAYED_INDEX,
                DeleteStallReviewCommand.MESSAGE_USAGE));
    }
}
