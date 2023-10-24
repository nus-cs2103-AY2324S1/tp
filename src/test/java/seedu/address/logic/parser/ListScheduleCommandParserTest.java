package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListScheduleCommand;
import seedu.address.model.schedule.TutorNameContainsKeywordsPredicate;

public class ListScheduleCommandParserTest {

    private ListScheduleCommandParser parser = new ListScheduleCommandParser();

    @Test
    public void parse_validArgs_returnsListScheduleCommand() {
        // no leading and trailing whitespaces
        ListScheduleCommand expectedFindCommand =
                new ListScheduleCommand(new TutorNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
