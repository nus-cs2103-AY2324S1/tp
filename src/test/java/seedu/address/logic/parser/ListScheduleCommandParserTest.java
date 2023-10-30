package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListScheduleCommand;

public class ListScheduleCommandParserTest {

    private ListScheduleCommandParser parser = new ListScheduleCommandParser();

    @Test
    public void parse_validArgs_returnsListScheduleCommand() {
        ListScheduleCommand expectedListCommand = new ListScheduleCommand(INDEX_FIRST_PERSON, null);
        assertParseSuccess(parser, "1", expectedListCommand);

    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nullArgs_returnsListScheduleCommand() {
        ListScheduleCommand expectedListCommand = new ListScheduleCommand(null, null);
        assertParseSuccess(parser, null, expectedListCommand);
    }

    @Test
    public void parse_emptyArgs_returnsListScheduleCommand() {
        ListScheduleCommand expectedListCommand = new ListScheduleCommand(null, null);
        assertParseSuccess(parser, "", expectedListCommand);
        assertParseSuccess(parser, "  ", expectedListCommand);
    }

}
