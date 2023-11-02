package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortTasksCommand;

class SortTasksCommandParserTest {
    private static final String COMPARATOR_TYPE_DESCRIPTION = "Description";
    private SortTasksCommandParser parser = new SortTasksCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTasksCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortTasksCommand() {
        SortTasksCommand expectedSortTasksCommand = new SortTasksCommand(COMPARATOR_TYPE_DESCRIPTION);
        assertParseSuccess(parser, "Description", expectedSortTasksCommand);

        assertParseSuccess(parser, " \n \t Description", expectedSortTasksCommand);
    }
}
