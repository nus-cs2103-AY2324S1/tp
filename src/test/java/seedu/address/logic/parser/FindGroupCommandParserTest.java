package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.model.person.GroupContainsKeywordsPredicate;

public class FindGroupCommandParserTest {
    private FindGroupCommandParser parser = new FindGroupCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindGroupCommand() {
        // no leading and trailing whitespaces
        FindGroupCommand expectedFindGroupCommand =
                new FindGroupCommand(new GroupContainsKeywordsPredicate(Arrays.asList("Group1", "Group2")));
        assertParseSuccess(parser, "Group1 Group2", expectedFindGroupCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Group1 \n \t Group2  \t", expectedFindGroupCommand);
    }

}
