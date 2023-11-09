package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalTags.INVALID_TAG_STRING;
import static seedu.address.testutil.TypicalTags.TEST_TAG_NAME_STRING;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;
import seedu.address.model.person.StatusContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalPredicateLists;


/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    // Delete by index tests
    @Test
    public void parse_validIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    // Delete by tags tests
    @Test
    public void parse_validTags_returnsDeleteCommand() {
        String userInput = " " + CliSyntax.PREFIX_TAG + TEST_TAG_NAME_STRING;

        // Create predicate based on the expected tag value
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(List.of(TEST_TAG_NAME_STRING));
        List<Predicate<Person>> predicateList = new ArrayList<>() {{
                add(tagPredicate);
            }};

        // Create a DeleteCommand with the tag predicate
        DeleteCommand expectedCommand = new DeleteCommand(predicateList);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidTags_throwsParseException() {
        String userInput = " " + PREFIX_TAG + INVALID_TAG_STRING;
        assertParseFailure(parser, userInput, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptyTags_throwsParseException() {
        String userInput = " " + PREFIX_TAG;
        assertParseFailure(parser, userInput, Tag.MESSAGE_CONSTRAINTS);
    }

    // Delete by status tests
    @Test
    public void parse_validStatus_returnsDeleteCommand() {
        String testStatus = new Status().toString(); // "Preliminary"
        String userInput = " " + CliSyntax.PREFIX_STATUS + testStatus;

        // Create predicate based on the expected status
        StatusContainsKeywordsPredicate statusPredicate = new StatusContainsKeywordsPredicate(List.of(testStatus));
        List<Predicate<Person>> predicateList = new ArrayList<>() {{
                add(statusPredicate);
            }};

        // Create a DeleteCommand with the status predicate
        DeleteCommand deleteCommand = new DeleteCommand(predicateList);
        assertParseSuccess(parser, userInput, deleteCommand);
    }

    @Test
    public void parse_invalidStatus_throwsParseException() {
        assertParseFailure(parser, PREFIX_STATUS.toString(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validTagsAndStatus_returnsDeleteCommand() {
        String userInput = " " + PREFIX_TAG + TEST_TAG_NAME_STRING + " "
                + PREFIX_STATUS + TypicalPredicateLists.TEST_STATUS_ONE;
        List<Predicate<Person>> predicateList = TypicalPredicateLists.PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_ONE;

        // Create a DeleteCommand with the tag predicate
        DeleteCommand expectedCommand = new DeleteCommand(predicateList);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidTagsButValidStatus_throwsParseException() {
        String userInput = " " + PREFIX_TAG + " " + PREFIX_STATUS + TypicalPredicateLists.TEST_STATUS_ONE;
        assertParseFailure(parser, userInput, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidStatusButValidTags_throwsParseException() {
        String userInput = " " + PREFIX_TAG + TEST_TAG_NAME_STRING + " " + PREFIX_STATUS; // empty status
        assertParseFailure(parser, userInput, Status.MESSAGE_CONSTRAINTS);
    }
}
