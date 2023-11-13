package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkCommand;

public class MarkCommandParserTest {
    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_validArgs_returnsMarkCommand() {
        assertParseSuccess(parser, "1", new MarkCommand(INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsInvalidFormatException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkCommand.COMMAND_WORD) + "\nUsage: " + MarkCommand.MESSAGE_USAGE;

        assertParseFailure(parser, "a", expectedMessage);
    }
}
