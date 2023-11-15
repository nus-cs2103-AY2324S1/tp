package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindStallCommand;
import seedu.address.model.util.NameContainsKeywordsPredicate;

public class FindStallCommandParserTest {

    private FindStallCommandParser parser = new FindStallCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // EP: No keywords
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStallCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStallCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindStallCommand() {
        // EP: One keyword
        FindStallCommand expectedFindStallCommand =
                new FindStallCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice")));
        assertParseSuccess(parser, "Alice", expectedFindStallCommand);

        // EP: Multiple keywords
        // no leading and trailing whitespaces
        expectedFindStallCommand =
                new FindStallCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindStallCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindStallCommand);
    }

}
