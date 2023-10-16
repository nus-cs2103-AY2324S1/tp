package seedu.lovebook.logic.parser;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.lovebook.logic.commands.FindCommand;
import seedu.lovebook.model.person.NameContainsKeywordsPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
