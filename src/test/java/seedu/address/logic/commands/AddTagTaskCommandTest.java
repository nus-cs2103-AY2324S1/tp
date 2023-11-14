package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddTagTaskCommand.
 */
public class AddTagTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        Tag tag = new Tag("class");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        AddTagTaskCommand addTagTaskCommand = new AddTagTaskCommand(INDEX_FIRST, tags);

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        Set<Tag> existingTags = taskToEdit.getTags();
        Set<Tag> updatedTags = new HashSet<>(existingTags);
        updatedTags.add(tag);

        Set<Tag> newTags = new HashSet<>();
        Set<Tag> oldTags = new HashSet<>();

        Set<Tag> commonTags = new HashSet<>(existingTags);
        commonTags.retainAll(tags);

        oldTags.addAll(commonTags);

        newTags.addAll(tags);
        newTags.removeAll(existingTags);

        Task editedTask = new TaskBuilder(taskToEdit).setTags(updatedTags).build();

        String expectedMessage = String.format(AddTagTaskCommand.MESSAGE_EDIT_TASK_SUCCESS,
                Messages.format(editedTask), AddTagTaskCommand.setToString(newTags),
                AddTagTaskCommand.setToString(oldTags));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(addTagTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Tag tag = new Tag("class");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        AddTagTaskCommand addTagTaskCommand = new AddTagTaskCommand(outOfBoundIndex, tags);

        assertCommandFailure(addTagTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_filteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST);

        Tag tag = new Tag("class");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        AddTagTaskCommand addTagTaskCommand = new AddTagTaskCommand(INDEX_FIRST, tags);

        Set<Tag> existingTags = taskToEdit.getTags();
        Set<Tag> updatedTags = new HashSet<>(existingTags);
        updatedTags.add(tag);

        Set<Tag> newTags = new HashSet<>();
        Set<Tag> oldTags = new HashSet<>();

        Set<Tag> commonTags = new HashSet<>(existingTags);
        commonTags.retainAll(tags);

        oldTags.addAll(commonTags);

        newTags.addAll(tags);
        newTags.removeAll(existingTags);

        Task editedTask = new TaskBuilder(taskToEdit).setTags(updatedTags).build();

        String expectedMessage = String.format(AddTagTaskCommand.MESSAGE_EDIT_TASK_SUCCESS,
                Messages.format(editedTask), AddTagTaskCommand.setToString(newTags),
                AddTagTaskCommand.setToString(oldTags));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTask(taskToEdit, editedTask);

        assertCommandSuccess(addTagTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Tag tag = new Tag("class");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        showTaskAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        AddTagTaskCommand addTagTaskCommand = new AddTagTaskCommand(outOfBoundIndex, tags);

        assertCommandFailure(addTagTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Tag tag = new Tag("class");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        AddTagTaskCommand addTagTaskCommand1 = new AddTagTaskCommand(INDEX_FIRST, tags);
        AddTagTaskCommand addTagTaskCommand2 = new AddTagTaskCommand(INDEX_FIRST, tags);

        // Different object -> returns false
        assertFalse(addTagTaskCommand1.equals(1));

        // Same values -> returns true
        assertTrue(addTagTaskCommand1.equals(addTagTaskCommand1));
        assertTrue(addTagTaskCommand1.equals(addTagTaskCommand2));

        // Different index -> returns false
        AddTagTaskCommand addTagTaskCommandDifferentIndex = new AddTagTaskCommand(INDEX_SECOND, tags);
        assertFalse(addTagTaskCommand1.equals(addTagTaskCommandDifferentIndex));

        // Different tags -> returns false
        Set<Tag> differentTags = new HashSet<>();
        differentTags.add(new Tag("differentTag"));
        AddTagTaskCommand addTagTaskCommandDifferentTags = new AddTagTaskCommand(INDEX_FIRST, differentTags);
        assertFalse(addTagTaskCommand1.equals(addTagTaskCommandDifferentTags));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Tag tag = new Tag("class");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        AddTagTaskCommand addTagTaskCommand = new AddTagTaskCommand(INDEX_FIRST, tags);
        String expected = AddTagTaskCommand.class.getCanonicalName() + "{index=" + index + ", tagsToAdd="
                + tags + "}";
        assertEquals(expected, addTagTaskCommand.toString());
    }

}
