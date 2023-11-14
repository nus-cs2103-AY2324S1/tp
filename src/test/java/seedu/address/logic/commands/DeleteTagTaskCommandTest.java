package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOCATION;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class DeleteTagTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_LOCATION));
        DeleteTagTaskCommand deleteTagTaskCommand = new DeleteTagTaskCommand(INDEX_FIRST, tags);

        // Starting state of first task only has location tag
        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());

        // Manually remove location tag
        Set<Tag> newTags = new HashSet<>(taskToEdit.getTags());
        newTags.remove(new Tag(VALID_TAG_LOCATION));

        Task editedTask = new Task(
                taskToEdit.getTitle(),
                taskToEdit.getNote(),
                taskToEdit.getStatus(),
                newTags);

        String expectedMessage = DeleteTagTaskCommand.formatResultMessage(taskToEdit, editedTask, tags);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(deleteTagTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeleteTagTaskCommand deleteTagTaskCommand = new DeleteTagTaskCommand(outOfBoundIndex,
                new HashSet<>());

        assertCommandFailure(deleteTagTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void formatResultMessage_matching() {
        Task taskToEdit = new TaskBuilder().withTags(VALID_TAG_FINANCE, VALID_TAG_LOCATION).build();

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_FINANCE));
        tags.add(new Tag(VALID_TAG_LOCATION));

        Task editTask = new TaskBuilder(taskToEdit).withTags(VALID_TAG_FINANCE, VALID_TAG_LOCATION).build();

        Set<Tag> matchingTag = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_FINANCE), new Tag(VALID_TAG_LOCATION)));
        String matchingTagsString = matchingTag.stream().map(Tag::toString).collect(Collectors.joining(", "));

        String expectedString = String.format(DeleteTagTaskCommand.MESSAGE_DELETE_TAG_TASK_SUCCESS,
                Messages.format(editTask))
                + String.format(DeleteTagTaskCommand.MESSAGE_DELETE_TAG_TASK_MATCHING_TAGS, matchingTagsString);

        assertEquals(expectedString, DeleteTagTaskCommand.formatResultMessage(taskToEdit, editTask, tags));
    }

    @Test
    public void formatResultMessage_missing() {
        Task taskToEdit = new TaskBuilder().withTags(VALID_TAG_FINANCE, VALID_TAG_FINANCE).build();

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("blabla"));

        Task editTask = new Task(
                taskToEdit.getTitle(),
                taskToEdit.getNote(),
                taskToEdit.getStatus(),
                new HashSet<>());

        Set<Tag> missingTag = new HashSet<>(Arrays.asList(new Tag("blabla")));
        String missingTagsString = missingTag.stream().map(Tag::toString).collect(Collectors.joining(", "));

        String expectedString = String.format(DeleteTagTaskCommand.MESSAGE_DELETE_TAG_TASK_SUCCESS,
                Messages.format(editTask))
                + String.format(DeleteTagTaskCommand.MESSAGE_DELETE_TAG_TASK_MISSING_TAGS, missingTagsString);

        assertEquals(expectedString, DeleteTagTaskCommand.formatResultMessage(taskToEdit, editTask, tags));
    }

    @Test
    public void formatResultMessage_matchingAndMissing() {
        Task taskToEdit = new TaskBuilder().withTags(VALID_TAG_LOCATION, VALID_TAG_FINANCE).build();

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_LOCATION));
        tags.add(new Tag("blabla"));

        Task editTask = new TaskBuilder(taskToEdit).withTags(VALID_TAG_FINANCE).build();

        Set<Tag> matchingTag = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_LOCATION)));
        Set<Tag> missingTag = new HashSet<>(Arrays.asList(new Tag("blabla")));
        String matchingTagsString = matchingTag.stream().map(Tag::toString).collect(Collectors.joining(", "));
        String missingTagsString = missingTag.stream().map(Tag::toString).collect(Collectors.joining(", "));

        String expectedString = String.format(DeleteTagTaskCommand.MESSAGE_DELETE_TAG_TASK_SUCCESS,
                Messages.format(editTask))
                + String.format(DeleteTagTaskCommand.MESSAGE_DELETE_TAG_TASK_MATCHING_TAGS, matchingTagsString)
                + String.format(DeleteTagTaskCommand.MESSAGE_DELETE_TAG_TASK_MISSING_TAGS, missingTagsString);

        assertEquals(expectedString, DeleteTagTaskCommand.formatResultMessage(taskToEdit, editTask, tags));
    }

    @Test
    public void equals() {
        DeleteTagTaskCommand deleteTagTaskFirstCommand = new DeleteTagTaskCommand(INDEX_FIRST, new HashSet<>());
        DeleteTagTaskCommand deleteTagTaskSecondCommand = new DeleteTagTaskCommand(INDEX_SECOND, new HashSet<>());

        // same object -> returns true
        assertTrue(deleteTagTaskFirstCommand.equals(deleteTagTaskFirstCommand));

        // same values -> returns true
        DeleteTagTaskCommand deleteTagTaskFirstCommandCopy = new DeleteTagTaskCommand(INDEX_FIRST,
                new HashSet<>());
        assertTrue(deleteTagTaskFirstCommand.equals(deleteTagTaskFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteTagTaskFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteTagTaskFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteTagTaskFirstCommand.equals(deleteTagTaskSecondCommand));

        // different tags -> returns false
        DeleteTagTaskCommand deleteTagTaskThirdCommand = new DeleteTagTaskCommand(INDEX_FIRST,
                new HashSet<>(Arrays.asList(new Tag("new"))));
        assertFalse(deleteTagTaskFirstCommand.equals(deleteTagTaskThirdCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_LOCATION));

        DeleteTagTaskCommand deleteTagTaskCommand = new DeleteTagTaskCommand(targetIndex, tags);
        String expected = DeleteTagTaskCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex
                + ", tagsToDelete="
                + tags + "}";
        assertEquals(expected, deleteTagTaskCommand.toString());
    }
}
