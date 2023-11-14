package seedu.lovebook.logic.parser;
import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.lovebook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.lovebook.logic.commands.UnstarCommand;



public class UnstarCommandParserTest {
    private UnstarCommandParser parser = new UnstarCommandParser();
    @Test
    public void parse_validArgs_returnsUnstarCommand() {
        assertParseSuccess(parser, "1", new UnstarCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnstarCommand.MESSAGE_USAGE));
    }
}
