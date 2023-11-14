package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();
    private String whitespace = " ";
    private String keywordsWithoutWhitespace = whitespace + PREFIX_NAME + "Amy Bob";
    private String keywordsWithWhitespace = whitespace + PREFIX_NAME + " \n Amy \n \t Bob  \t";
    private String keywordsNoPrefix = whitespace + "Amy Bob";

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // valid command format for find person
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + keywordsWithoutWhitespace,
                new FindPersonCommand(new NameContainsKeywordsPredicate(
                        Arrays.asList("Amy", "Bob")))); // no leading and trailing whitespaces

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + keywordsWithWhitespace,
                new FindPersonCommand(new NameContainsKeywordsPredicate(
                        Arrays.asList("Amy", "Bob")))); // multiple whitespaces between keywords

        // valid command format for find group
        assertParseSuccess(parser, NAME_DESC_CS,
                new FindGroupCommand(VALID_GROUP_CS));
    }

    @Test
    public void parse_compulsoryFieldMissingPrefix_failure() {
        // empty arguments
        assertParseFailure(parser, PREAMBLE_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // missing name prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + keywordsNoPrefix,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // missing group prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_GROUP_CS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        // multiple name prefixes
        assertParseFailure(parser, PREAMBLE_WHITESPACE + keywordsWithoutWhitespace + keywordsWithWhitespace,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple group prefixes
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS + NAME_DESC_CS2103T,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GROUPTAG));

        //multiple name and group prefixes
        assertParseFailure(parser, PREAMBLE_WHITESPACE + keywordsWithoutWhitespace + keywordsWithWhitespace
                        + NAME_DESC_CS + NAME_DESC_CS2103T,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_GROUPTAG));

        // name and group
        assertParseFailure(parser, PREAMBLE_WHITESPACE + keywordsWithoutWhitespace + NAME_DESC_CS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_TWO_PARAMETERS));
    }

    @Test
    public void parse_invalidValue_failure() {
        // empty name for find person command
        assertParseFailure(parser, PREAMBLE_WHITESPACE + whitespace + PREFIX_NAME,
                FindCommand.MESSAGE_EMPTY_NAME);
    }
}
