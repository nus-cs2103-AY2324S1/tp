package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PROJECT1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PROJECT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FULL_PROJECT_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_APPLEAPP;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertProjectCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.address.testutil.TypicalProjects.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.testutil.EditProjectDescriptorBuilder;
import seedu.address.testutil.ProjectBuilder;

public class EditProjectCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Project editedProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        EditProjectCommand.EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(editedProject).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST_PROJECT, descriptor);

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS,
                Messages.format(editedProject));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);

        assertProjectCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProject = Index.fromOneBased(model.getFilteredProjectList().size());
        Project lastProject = model.getFilteredProjectList().get(indexLastProject.getZeroBased());

        ProjectBuilder projectInList = new ProjectBuilder(lastProject);
        Project editedProject = projectInList.withDescription(VALID_PROJECT_DESCRIPTION_APPLEAPP)
                .withDeadline(VALID_FULL_PROJECT_DEADLINE_1).build();

        EditProjectCommand.EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withDescription(VALID_PROJECT_DESCRIPTION_APPLEAPP)
                .withDeadlines(VALID_FULL_PROJECT_DEADLINE_1).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(indexLastProject, descriptor);

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS,
                Messages.format(editedProject));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProject(lastProject, editedProject);

        assertProjectCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST_PROJECT,
                new EditProjectCommand.EditProjectDescriptor());
        Project editedProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS,
                Messages.format(editedProject));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertProjectCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Project projectInFilteredList = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        Project editedProject = new ProjectBuilder(projectInFilteredList)
                .withDescription(VALID_PROJECT_DESCRIPTION_APPLEAPP).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST_PROJECT,
                new EditProjectDescriptorBuilder().withDescription(VALID_PROJECT_DESCRIPTION_APPLEAPP).build());

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS,
                Messages.format(editedProject));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);

        assertProjectCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateProjectDetailsUnfilteredList_success() {
        Project firstProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        Project secondProject = model.getFilteredProjectList().get(INDEX_SECOND_PROJECT.getZeroBased());
        EditProjectCommand.EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(firstProject).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_SECOND_PROJECT, descriptor);

        Project editedProject = new ProjectBuilder(secondProject)
                .withDescription(firstProject.getProjectDescription().desc)
                .withDeadlines(firstProject.getProjectDeadlines()).build();

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS,
                Messages.format(editedProject));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(1), editedProject);

        assertProjectCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProjectIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        EditProjectCommand.EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withDescription(VALID_PROJECT_DESCRIPTION_APPLEAPP).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditProjectCommand standardCommand = new EditProjectCommand(INDEX_FIRST_PROJECT, DESC_PROJECT1);

        // same values -> returns true
        EditProjectCommand.EditProjectDescriptor copyDescriptor = new EditProjectCommand.EditProjectDescriptor(
                DESC_PROJECT1);
        EditProjectCommand commandWithSameValues = new EditProjectCommand(INDEX_FIRST_PROJECT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditProjectCommand(INDEX_SECOND_PROJECT, DESC_PROJECT1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditProjectCommand(INDEX_FIRST_PROJECT, DESC_PROJECT2)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditProjectCommand.EditProjectDescriptor editProjectDescriptor = new EditProjectCommand.EditProjectDescriptor();
        EditProjectCommand editProjectCommand = new EditProjectCommand(index, editProjectDescriptor);
        String expected = EditProjectCommand.class.getCanonicalName() + "{index=" + index
                + ", editProjectDescriptor=" + editProjectDescriptor + "}";
        assertEquals(expected, editProjectCommand.toString());
    }
}
