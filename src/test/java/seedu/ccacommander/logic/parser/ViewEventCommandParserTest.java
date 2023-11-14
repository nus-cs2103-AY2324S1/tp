package seedu.ccacommander.logic.parser;

import static seedu.ccacommander.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ccacommander.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ccacommander.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.logic.commands.ViewEventCommand;

public class ViewEventCommandParserTest {
    private ViewEventCommandParser parser = new ViewEventCommandParser();

    @Test
    public void parse_validArgs_returnsViewEventCommand() {
        assertParseSuccess(parser, "1", new ViewEventCommand(INDEX_FIRST_EVENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewEventCommand.MESSAGE_USAGE));
    }
}
