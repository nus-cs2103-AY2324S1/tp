package seedu.letsgethired.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.DESC_BYTEDANCE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.DESC_JANE_STREET;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_BYTEDANCE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_BACK_END;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.letsgethired.logic.commands.CommandTestUtil.showInternApplicationAtIndex;
import static seedu.letsgethired.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.logic.commands.EditCommand.EditInternApplicationDescriptor;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ModelManager;
import seedu.letsgethired.model.UserPrefs;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.testutil.EditInternApplicationDescriptorBuilder;
import seedu.letsgethired.testutil.InternApplicationBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalInternTracker(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        InternApplication editedInternApplication = new InternApplicationBuilder().build();
        EditInternApplicationDescriptor descriptor =
                new EditInternApplicationDescriptorBuilder(editedInternApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, descriptor);

        String expectedMessage = EditCommand.MESSAGE_EDIT_INTERN_APPLICATION_SUCCESS;
        InternApplication expectedDetails = editedInternApplication;

        Model expectedModel = new ModelManager(new InternTracker(model.getInternTracker()), new UserPrefs());
        expectedModel.setInternApplication(model.getFilteredInternApplicationList().get(0), editedInternApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedDetails, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastInternApplication = Index.fromOneBased(model.getFilteredInternApplicationList().size());
        InternApplication lastInternApplication = model.getFilteredInternApplicationList()
                .get(indexLastInternApplication.getZeroBased());

        InternApplicationBuilder internApplicationInList = new InternApplicationBuilder(lastInternApplication);
        InternApplication editedInternApplication = internApplicationInList
                .withCompany(VALID_COMPANY_BYTEDANCE)
                .withRole(VALID_ROLE_BACK_END).build();

        EditInternApplicationDescriptor descriptor = new EditInternApplicationDescriptorBuilder()
                .withCompany(VALID_COMPANY_BYTEDANCE)
                .withRole(VALID_ROLE_BACK_END)
                .build();
        EditCommand editCommand = new EditCommand(indexLastInternApplication, descriptor);

        String expectedMessage = EditCommand.MESSAGE_EDIT_INTERN_APPLICATION_SUCCESS;
        InternApplication expectedDetails = editedInternApplication;

        Model expectedModel = new ModelManager(new InternTracker(model.getInternTracker()), new UserPrefs());
        expectedModel.setInternApplication(lastInternApplication, editedInternApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedDetails, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, new EditInternApplicationDescriptor());
        InternApplication editedInternApplication = model.getFilteredInternApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());

        String expectedMessage = EditCommand.MESSAGE_EDIT_INTERN_APPLICATION_SUCCESS;
        InternApplication expectedDetails = editedInternApplication;

        Model expectedModel = new ModelManager(new InternTracker(model.getInternTracker()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedDetails, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        model.updateFilteredInternApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);

        InternApplication internApplicationInFilteredList = model.getFilteredInternApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        InternApplication editedInternApplication = new InternApplicationBuilder(internApplicationInFilteredList)
                .withCompany(VALID_COMPANY_BYTEDANCE).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditInternApplicationDescriptorBuilder().withCompany(VALID_COMPANY_BYTEDANCE).build());

        String expectedMessage = EditCommand.MESSAGE_EDIT_INTERN_APPLICATION_SUCCESS;

        Model expectedModel = new ModelManager(new InternTracker(model.getInternTracker()), new UserPrefs());
        expectedModel.setInternApplication(model.getFilteredInternApplicationList().get(0), editedInternApplication);
        expectedModel.updateFilteredInternApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);

        assertCommandSuccess(editCommand, model, expectedMessage, editedInternApplication, expectedModel);
    }

    @Test
    public void execute_duplicateInternApplicationUnfilteredList_failure() {
        InternApplication firstInternApplication = model.getFilteredInternApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        EditInternApplicationDescriptor descriptor =
                new EditInternApplicationDescriptorBuilder(firstInternApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_APPLICATION, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_INTERN_APPLICATION);
    }

    @Test
    public void execute_duplicateInternApplicationFilteredList_failure() {
        showInternApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        // edit intern application in filtered list into a duplicate in intern tracker
        InternApplication internApplicationInList = model.getInternTracker().getApplicationList()
                .get(INDEX_SECOND_APPLICATION.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditInternApplicationDescriptorBuilder(internApplicationInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_INTERN_APPLICATION);
    }

    @Test
    public void execute_invalidInternApplicationIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternApplicationList().size() + 1);
        EditInternApplicationDescriptor descriptor = new EditInternApplicationDescriptorBuilder()
                .withCompany(VALID_COMPANY_BYTEDANCE).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of intern tracker
     */
    @Test
    public void execute_invalidInternApplicationIndexFilteredList_failure() {
        showInternApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of intern tracker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternTracker().getApplicationList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditInternApplicationDescriptorBuilder().withCompany(VALID_COMPANY_BYTEDANCE).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_APPLICATION, DESC_JANE_STREET);

        // same values -> returns true
        EditInternApplicationDescriptor copyDescriptor = new EditInternApplicationDescriptor(DESC_JANE_STREET);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_APPLICATION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_APPLICATION, DESC_JANE_STREET)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_APPLICATION, DESC_BYTEDANCE)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditInternApplicationDescriptor editInternApplicationDescriptor = new EditInternApplicationDescriptor();
        EditCommand editCommand = new EditCommand(index, editInternApplicationDescriptor);
        String expected = EditCommand.class.getCanonicalName()
                + "{index=" + index + ", editInternApplicationDescriptor="
                + editInternApplicationDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
