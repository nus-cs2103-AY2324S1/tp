package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindItemCommand;
import seedu.address.model.util.MenuContainsKeywordsPredicate;

public class FindItemCommandParserTest {

    private FindItemCommandParser parser = new FindItemCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // EP: No keywords
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindItemCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindItemCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindItemCommand() {
        // EP: One keyword
        FindItemCommand expectedFindItemCommand =
                new FindItemCommand(new MenuContainsKeywordsPredicate(Arrays.asList("chicken")));
        assertParseSuccess(parser, "chicken", expectedFindItemCommand);

        // EP: Multiple keywords
        // no leading and trailing whitespaces
        expectedFindItemCommand =
                new FindItemCommand(new MenuContainsKeywordsPredicate(Arrays.asList("chicken", "rice")));
        assertParseSuccess(parser, "chicken rice", expectedFindItemCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n chicken \n \t rice  \t", expectedFindItemCommand);
    }

}
