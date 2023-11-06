package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.commands.FindCommand.MESSAGE_TOO_LONG;
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
    public void parse_symbolsArg_throwsParseException() {
        //parser should not accept strings with symbols in it
        String userInput = "*";

        assertParseFailure(parser, userInput,
                String.format(MESSAGE_WRONG_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_tooLong_throwsParseException() {
        //parser should not accept strings with more than 55 chars
        String userInput = "IDe2WzICYFSHOqGIL7mnsnvGcvFuq4T20FAdmU1p5MFHuWMz57CGX29S";

        assertParseFailure(parser, userInput,
                String.format(MESSAGE_TOO_LONG, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_justRightLength_returnsFindCommand() {
        //parser should accept strings with exactly 55 chars
        String userInput = "2hf5VLvv5se2nRcHgvvctJ4zjoZMCSYnTfYn0m6tHVUFPmHHtfhjmaL";
        FindCommand expectedCommand = new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(userInput)));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_rightLength_returnsFindCommand() {
        //parser should accept strings with less than 55 chars
        String userInput = "Alice";
        FindCommand expectedCommand = new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(userInput)));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleValidArgs_returnsFindCommand() {
        //parser should accept multiple valid keywords
        String userInput = "Alice Bob";
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));

        assertParseSuccess(parser, userInput, expectedFindCommand);
    }

    @Test
    public void parse_leadingWhitespace_returnsFindCommand() {
        //parser should accept valid keywords with leading whitespaces
        String userInput = "      \n Alice";
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice")));

        assertParseSuccess(parser, userInput, expectedFindCommand);
    }

    @Test
    public void parse_trailingWhitespace_returnsFindCommand() {
        //parser should accept valid keywords with trailing whitespaces
        String userInput = "Alice        \n";
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice")));

        assertParseSuccess(parser, userInput, expectedFindCommand);
    }

    @Test
    public void parse_whitespaceBetweenKeywords_returnsFindCommand() {
        //parser should accept valid keywords with whitespaces between them
        String userInput = "Alice            Bob";
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));

        assertParseSuccess(parser, userInput, expectedFindCommand);
    }

}
