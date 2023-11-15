package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.prescription.NameContainsKeywordsPredicate;

public class FindCommandParserTest {
    private FindCommandParser parser = new FindCommandParser();

    //write some test cases for the parser
    // EP: non empty string
    @Test
    public void parse_nonEmptyString_success() {
        String userInput = "abc";
        assertParseSuccess(parser, userInput,
                new FindCommand(new NameContainsKeywordsPredicate(List.of("abc"))));
    }

    // EP: empty string
    @Test
    public void parse_emptyString_failure() {
        String userInput = "";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindCommand.MESSAGE_USAGE));
    }

    // EP: null string
    @Test
    public void parse_nullString_failure() {
        String userInput = null;
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindCommand.MESSAGE_USAGE));
    }

    // EP: multiple keywords malicious input
    @Test
    public void parse_extraValues_success() {
        // Random prefixes
        assertParseSuccess(parser, "mn/ABCD d/2",
                new FindCommand(new NameContainsKeywordsPredicate(
                        List.of("mn/ABCD", "d/2"))));
    }
}
