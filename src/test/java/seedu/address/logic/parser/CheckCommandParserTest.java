package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CheckCommand;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

public class CheckCommandParserTest {

    private CheckCommandParser parser = new CheckCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsCheckCommand() {
        // no leading and trailing whitespaces
        CheckCommand expectedCheckCommand = new CheckCommand(1, new GroupContainsKeywordsPredicate(Arrays.asList("1")));
        assertParseSuccess(parser, "1", expectedCheckCommand);

        // multiple whitespaces before and after group number
        assertParseSuccess(parser, " \n 1 \n \t", expectedCheckCommand);
    }
}
