package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.commands.DeleteCourseCommand;

public class DeleteCourseCommandParserTest {

    private DeleteCourseCommandParser parser = new DeleteCourseCommandParser();

    @Test
    public void parse_validIndex_returnsDeleteCourseCommand() {
        assertParseSuccess(parser, "1", new DeleteCourseCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCourseCommand.MESSAGE_USAGE));
    }
}
