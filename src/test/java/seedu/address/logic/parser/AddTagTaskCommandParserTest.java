package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LOCATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagTaskCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

public class AddTagTaskCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagTaskCommand.MESSAGE_USAGE);

    private AddTagTaskCommandParser parser = new AddTagTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TAG_LOCATION, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TAG_DESC_LOCATION, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TAG_DESC_LOCATION, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_LOCATION + TAG_DESC_FINANCE + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_LOCATION + TAG_EMPTY + TAG_DESC_FINANCE, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_LOCATION + TAG_DESC_FINANCE, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Tag tag = new Tag(VALID_TAG_LOCATION);
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + TAG_DESC_LOCATION;

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Task taskToEdit = model.getFilteredTaskList().get(targetIndex.getZeroBased());

        Set<Tag> existingTags = taskToEdit.getTags();
        Set<Tag> updatedTags = new HashSet<>(existingTags);
        updatedTags.add(tag);

        AddTagTaskCommand expectedCommand = new AddTagTaskCommand(targetIndex, tags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
