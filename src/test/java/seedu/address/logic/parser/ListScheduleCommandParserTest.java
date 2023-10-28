package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListScheduleCommand;

public class ListScheduleCommandParserTest {

    private ListScheduleCommandParser parser = new ListScheduleCommandParser();

    @Test
    public void parse_validArgs_returnsListScheduleCommand() {
        ListScheduleCommand expectedListCommand = new ListScheduleCommand(INDEX_FIRST_PERSON);
        assertParseSuccess(parser, "1", expectedListCommand);

    }

}
