package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindMemberCommand;
import seedu.address.model.person.MemberContainsKeywordsPredicate;

public class FindMemberCommandParserTest {

    private FindMemberCommandParser parser = new FindMemberCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindMemberCommand expectedFindCommand =
                new FindMemberCommand(new MemberContainsKeywordsPredicate(Arrays.asList("Alicia", "Teng")));
        assertParseSuccess(parser, "Alicia Teng", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alicia \n \t Teng  \t", expectedFindCommand);
    }

}
