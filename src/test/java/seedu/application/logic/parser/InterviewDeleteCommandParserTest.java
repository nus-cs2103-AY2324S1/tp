package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.application.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.InterviewDeleteCommand;

class InterviewDeleteCommandParserTest {

    private InterviewDeleteCommandParser parser = new InterviewDeleteCommandParser();
    @Test
    void parse_validArgs_returnsInterviewDeleteCommand() {
        assertParseSuccess(parser, "1 from/3", new InterviewDeleteCommand(INDEX_THIRD, INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //no indexes indicated
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewDeleteCommand.MESSAGE_USAGE));
        //only job index indicated
        assertParseFailure(parser, "2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewDeleteCommand.MESSAGE_USAGE));
        //no job index indicated
        assertParseFailure(parser, "from/1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewDeleteCommand.MESSAGE_USAGE));
        //prefix not indicated
        assertParseFailure(parser, "1 4",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewDeleteCommand.MESSAGE_USAGE));
        //no interview index indicated
        assertParseFailure(parser, "1 from/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewDeleteCommand.MESSAGE_USAGE));
    }
}
