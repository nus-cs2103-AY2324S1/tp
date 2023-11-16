package seedu.address.logic.commands.mark;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertProjectCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT_AND_DEADLINE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.address.testutil.TypicalProjects.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkDeadlineCommand.
 */
public class MarkDeadlineCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexesUnfilteredList_success() {
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        Index projectIndex = INDEX_FIRST_PROJECT_AND_DEADLINE.get(0);
        Index deadlineIndex = INDEX_FIRST_PROJECT_AND_DEADLINE.get(1);
        List<String> markedDeadline = projectToEdit.markDeadlineStringRep(deadlineIndex.getOneBased());

        MarkDeadlineCommand markDeadlineCommand = new MarkDeadlineCommand(projectIndex, deadlineIndex);

        ProjectBuilder projectInList = new ProjectBuilder(projectToEdit);
        Project editedProject = projectInList.withDeadlinesString(markedDeadline).build();

        String expectedMessage = MarkDeadlineCommand.MESSAGE_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setProject(projectToEdit, editedProject);

        assertProjectCommandSuccess(markDeadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProjectIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        MarkDeadlineCommand markDeadlineCommand = new MarkDeadlineCommand(outOfBoundIndex, INDEX_FIRST_PROJECT);

        assertCommandFailure(markDeadlineCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDeadlineIndexUnfilteredList_failure() {
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(projectToEdit.deadlineListSize() + 1);
        MarkDeadlineCommand markDeadlineCommand = new MarkDeadlineCommand(INDEX_FIRST_PROJECT, outOfBoundIndex);

        assertCommandFailure(markDeadlineCommand, model, Messages.MESSAGE_INVALID_DEADLINE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkDeadlineCommand markFirstCommand = new MarkDeadlineCommand(INDEX_FIRST_PROJECT, INDEX_FIRST_PROJECT);
        MarkDeadlineCommand markSecondCommand = new MarkDeadlineCommand(INDEX_SECOND_PROJECT, INDEX_SECOND_PROJECT);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkDeadlineCommand markFirstCommandCopy = new MarkDeadlineCommand(INDEX_FIRST_PROJECT, INDEX_FIRST_PROJECT);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different project index -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }

    @Test
    public void execute_validIndexesFilteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        Index projectIndex = INDEX_FIRST_PROJECT_AND_DEADLINE.get(0);
        Index deadlineIndex = INDEX_FIRST_PROJECT_AND_DEADLINE.get(1);
        List<String> markedDeadline = projectToEdit.markDeadlineStringRep(deadlineIndex.getOneBased());

        MarkDeadlineCommand markDeadlineCommand = new MarkDeadlineCommand(projectIndex, deadlineIndex);

        ProjectBuilder projectInList = new ProjectBuilder(projectToEdit);
        Project editedProject = projectInList.withDeadlinesString(markedDeadline).build();

        String expectedMessage = MarkDeadlineCommand.MESSAGE_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setProject(projectToEdit, editedProject);

        assertProjectCommandSuccess(markDeadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidDeadlineIndexFilteredList_failure() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(projectToEdit.deadlineListSize() + 1);
        MarkDeadlineCommand markDeadlineCommand = new MarkDeadlineCommand(INDEX_FIRST_PROJECT, outOfBoundIndex);

        assertCommandFailure(markDeadlineCommand, model, Messages.MESSAGE_INVALID_DEADLINE_DISPLAYED_INDEX);
    }
}
