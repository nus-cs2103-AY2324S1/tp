package seedu.ccacommander.logic.parser;

import static seedu.ccacommander.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ccacommander.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ccacommander.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.logic.commands.FindMemberCommand;
import seedu.ccacommander.model.member.MemberNameContainsKeywordsPredicate;

public class FindMemberCommandParserTest {

    private FindMemberCommandParser parser = new FindMemberCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindMemberCommand expectedFindMemberCommand =
                new FindMemberCommand(new MemberNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindMemberCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindMemberCommand);
    }

}
