package seedu.classmanager.logic.parser;

import static seedu.classmanager.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classmanager.logic.commands.CommandTestUtil.TEST_TUTORIAL;
import static seedu.classmanager.logic.commands.CommandTestUtil.TEST_TUTORIAL_DESC;
import static seedu.classmanager.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.classmanager.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.classmanager.commons.core.index.Index;
import seedu.classmanager.logic.commands.MarkPresentAllCommand;

/**
 * Tests MarkPresentAllCommandParser.
 */
public class MarkPresentAllCommandParserTest {
    private MarkPresentAllCommandParser parser = new MarkPresentAllCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkPresentAllCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkPresentAllCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsMarkCommand() {
        assertParseSuccess(parser, TEST_TUTORIAL_DESC,
                new MarkPresentAllCommand(Index.fromOneBased(TEST_TUTORIAL)));
    }
}
