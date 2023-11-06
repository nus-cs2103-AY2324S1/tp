package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.commands.FindCommand.MESSAGE_WRONG_FORMAT;
import static seedu.staffsnap.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.staffsnap.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.commands.FindCommand;
import seedu.staffsnap.model.applicant.NameContainsKeywordsPredicate;

/**
 * Tests the FindCommandParser to ensure it accepts and parses the correct user input.
 */
public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        //parser should not accept empty strings
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_numericalArg_throwsParseException() {
        //parser should not accept strings with numbers in it
        String userInput = "lee2";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_WRONG_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_symbolsArg_throwsParseException() {
        //parser should not accept strings with symbols in it
        String userInput = "*";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_WRONG_FORMAT, FindCommand.MESSAGE_USAGE));
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
