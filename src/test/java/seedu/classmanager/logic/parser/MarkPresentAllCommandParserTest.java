package seedu.classmanager.logic.parser;

import static seedu.classmanager.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classmanager.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.classmanager.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.classmanager.commons.core.index.Index;
import seedu.classmanager.logic.commands.MarkPresentAllCommand;
import seedu.classmanager.model.student.ClassDetails;

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
        int tut = 1;
        assertParseSuccess(parser, " " + tut,
                new MarkPresentAllCommand(Index.fromOneBased(tut)));
    }

    @Test
    public void parse_invalidTutorialIndex_throwsParseException() {
        ClassDetails.setTutorialCount(10);
        assertParseFailure(parser, " 0", ClassDetails.getMessageInvalidTutorialIndex());
        assertParseFailure(parser, " test", ClassDetails.getMessageInvalidTutorialIndex());
    }
}
