package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindRoleCommand;
import seedu.address.model.person.RoleContainsKeywordsPredicate;

public class FindRoleCommandParserTest {

    private FindRoleCommandParser parser = new FindRoleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindRoleCommand() {
        // no leading and trailing whitespaces
        FindRoleCommand expectedFindRoleCommand =
                new FindRoleCommand(new RoleContainsKeywordsPredicate(Arrays.asList("TA", "Student")));
        assertParseSuccess(parser, "TA Student", expectedFindRoleCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n TA \n \t Student  \t", expectedFindRoleCommand);

    }

}
