package seedu.letsgethired.logic.parser;

import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.logic.commands.ViewCommand;
public class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new ViewCommand(INDEX_FIRST_APPLICATION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand.MESSAGE_USAGE));
    }
}
