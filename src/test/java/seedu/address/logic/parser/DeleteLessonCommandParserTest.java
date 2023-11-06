package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteLessonCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteLessonCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the TypeParserUtilTest.
 */

class DeleteLessonCommandParserTest {
    private DeleteLessonCommandParser parser = new DeleteLessonCommandParser();
    @Test
    public void parse_validArgs_returnsDeleteLessonCommand() {
        DeleteLessonCommand c = new DeleteLessonCommand(INDEX_FIRST_LESSON);
        assertParseSuccess(parser, "1", new DeleteLessonCommand(INDEX_FIRST_LESSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLessonCommand.MESSAGE_USAGE));
    }

}
