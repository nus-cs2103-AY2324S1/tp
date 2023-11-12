package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Status;
import seedu.address.model.person.StatusContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    private UniqueTagList uniqueTagList = new UniqueTagList();

    @BeforeEach
    public void clearTagList() {
        if (uniqueTagList.contains(new Tag("developer", "role"))) {
            uniqueTagList.remove(new Tag("developer", "role"));
        }
        if (uniqueTagList.contains(new Tag("intern", "employment"))) {
            uniqueTagList.remove(new Tag("intern", "employment"));
        }
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        uniqueTagList.add(new Tag("developer", "role"));
        FindCommand expectedFindCommand =
                new FindCommand(Arrays.asList(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new StatusContainsKeywordsPredicate(Arrays.asList("Interviewed")),
                        new TagContainsKeywordsPredicate(Arrays.asList("developer"))));
        assertParseSuccess(parser, " n/Alice Bob st/Interviewed t/developer", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t st/Interviewed    \t t/developer",
                expectedFindCommand);

    }

    @Test
    public void parse_validArgsWithOnlyName_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(Arrays.asList(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t ", expectedFindCommand);
    }

    @Test
    public void parse_validArgsWithOnlyStatus_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(Arrays.asList(new StatusContainsKeywordsPredicate(Arrays.asList("preliminary",
                        "rejected"))));
        assertParseSuccess(parser, " st/preliminary rejected", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " st/ \n preliminary \n \t rejected  \t ", expectedFindCommand);
    }

    @Test
    public void parse_validArgsWithOnlyTag_returnsFindCommand() {
        // no leading and trailing whitespaces
        uniqueTagList.add(new Tag("developer", "role"));
        uniqueTagList.add(new Tag("intern", "employment"));
        FindCommand expectedFindCommand =
                new FindCommand(Arrays.asList(new TagContainsKeywordsPredicate(Arrays.asList("developer", "intern"))));
        assertParseSuccess(parser, " t/developer intern", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " t/ \n developer \n \t intern  \t ", expectedFindCommand);
    }

    @Test
    public void parse_validArgsWithOnlyNameAndStatus_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(Arrays.asList(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new StatusContainsKeywordsPredicate(Arrays.asList("offered", "rejected"))));
        assertParseSuccess(parser, " n/Alice Bob st/offered rejected", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t st/offered     \t rejected", expectedFindCommand);
    }

    @Test
    public void parse_invalidArgsWithInvalidName_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, " n/@lice Bob", Name.MESSAGE_CONSTRAINTS);

        // multiple whitespaces between keywords
        assertParseFailure(parser, " n/ \n @lice \n \t Bob  \t ", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgsWithInvalidStatus_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, " st/invalidStatus", Status.MESSAGE_CONSTRAINTS);

        // multiple whitespaces between keywords
        assertParseFailure(parser, " st/ \n invalidStatus ", Status.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgsWithInvalidTag_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, " t/invalidTag#", Tag.MESSAGE_CONSTRAINTS);

        // multiple whitespaces between keywords
        assertParseFailure(parser, " t/ \n invalidTag#  \t ", Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgsWithEmptyName_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, " n/", Name.MESSAGE_CONSTRAINTS);

        // multiple whitespaces between keywords
        assertParseFailure(parser, " n/ \n   \t ", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgsWithEmptyStatus_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, " st/", Status.MESSAGE_CONSTRAINTS);

        // multiple whitespaces between keywords
        assertParseFailure(parser, " st/ \n \n \t ", Status.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgsWithEmptyTag_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, " t/", Tag.MESSAGE_CONSTRAINTS);

        // multiple whitespaces between keywords
        assertParseFailure(parser, " t/ \n  \n \t   \t ", Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleNamePrefixes_throwsParseException() {
        assertParseFailure(parser, " n/alex n/bernice",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    }

    @Test
    public void parse_multipleStatusPrefixes_throwsParseException() {
        assertParseFailure(parser, " st/interviewed st/rejected",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));
    }

    @Test
    public void parse_multipleTagPrefixes_throwsParseException() {
        assertParseFailure(parser, " t/software t/developer",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TAG));
    }





}
