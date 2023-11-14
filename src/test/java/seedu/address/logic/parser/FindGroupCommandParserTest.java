package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

public class FindGroupCommandParserTest {

    private FindGroupCommandParser parser = new FindGroupCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "a", ParserUtil.MESSAGE_INVALID_GROUP_NUMBER);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a b", ParserUtil.MESSAGE_INVALID_GROUP_NUMBER);
    }

    @Test
    public void parse_validAndInvalidArgs_throwsParseException() {
        assertParseFailure(parser, "1 b", ParserUtil.MESSAGE_INVALID_GROUP_NUMBER);
    }

    @Test
    public void parse_validArg_returnsFindGroupCommand() {
        // no leading and trailing whitespaces
        FindGroupCommand expectedFindGroupCommand =
                new FindGroupCommand(new GroupContainsKeywordsPredicate(Arrays.asList("1")));
        assertParseSuccess(parser, "1", expectedFindGroupCommand);

        // multiple whitespaces before and after keyword
        assertParseSuccess(parser, " \n 1 \n \t", expectedFindGroupCommand);
    }

    @Test
    public void parse_validArgs_returnsFindGroupCommand() {
        // no leading and trailing whitespaces
        FindGroupCommand expectedFindGroupCommand =
                new FindGroupCommand(new GroupContainsKeywordsPredicate(Arrays.asList("1", "2")));
        assertParseSuccess(parser, "1 2", expectedFindGroupCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 1 \n \t 2  \t", expectedFindGroupCommand);
    }
}
