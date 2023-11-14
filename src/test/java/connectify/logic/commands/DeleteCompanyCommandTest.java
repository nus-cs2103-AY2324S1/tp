package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandFailure;
import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.company.Company;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCompanyCommand}.
 */
public class DeleteCompanyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Company companyToDelete = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(INDEX_FIRST_COMPANY);

        String expectedMessage = String.format(DeleteCompanyCommand.MESSAGE_DELETE_COMPANY_SUCCESS,
                Messages.format(companyToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteCompany(companyToDelete);
        for (int i = 0; i < companyToDelete.getPersonList().size(); i++) {
            expectedModel.deletePerson(companyToDelete.getPersonList().get(i));
        }

        assertCommandSuccess(deleteCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(outOfBoundIndex);

        assertCommandFailure(deleteCompanyCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Company companyToDelete = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(INDEX_FIRST_COMPANY);

        String expectedMessage = String.format(DeleteCompanyCommand.MESSAGE_DELETE_COMPANY_SUCCESS,
                Messages.format(companyToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteCompany(companyToDelete);
        for (int i = 0; i < companyToDelete.getPersonList().size(); i++) {
            expectedModel.deletePerson(companyToDelete.getPersonList().get(i));
        }

        showNoCompany(expectedModel);

        assertCommandSuccess(deleteCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Index outOfBoundIndex = INDEX_SECOND_COMPANY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCompanyList().size());

        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(outOfBoundIndex);

        assertCommandFailure(deleteCompanyCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deleteCompanyandPeople_success() {
        Company companyToDelete = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(INDEX_FIRST_COMPANY);

        String expectedMessage = String.format(DeleteCompanyCommand.MESSAGE_DELETE_COMPANY_SUCCESS,
                Messages.format(companyToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        System.out.println(companyToDelete.getPersonList().size());
        expectedModel.deleteCompany(companyToDelete);
        for (int i = 0; i < companyToDelete.getPersonList().size(); i++) {
            expectedModel.deletePerson(companyToDelete.getPersonList().get(i));
        }

        assertCommandSuccess(deleteCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteCompanyDeletesPeople_success() {
        Company companyToDelete = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        ModelManager originalModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(INDEX_FIRST_COMPANY);
        try {
            deleteCompanyCommand.execute(model);
        } catch (CommandException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }

        int numPeople = companyToDelete.getPersonList().size();
        assertEquals(originalModel.getFilteredPersonList().size() - numPeople, model.getFilteredPersonList().size());
    }

    @Test
    public void equals() {
        DeleteCompanyCommand deleteFirstCommand = new DeleteCompanyCommand(INDEX_FIRST_COMPANY);
        DeleteCompanyCommand deleteSecondCommand = new DeleteCompanyCommand(INDEX_SECOND_COMPANY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCompanyCommand deleteFirstCommandCopy = new DeleteCompanyCommand(INDEX_FIRST_COMPANY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(targetIndex);
        String expected = DeleteCompanyCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCompanyCommand.toString());
    }


    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCompany(Model model) {
        model.updateFilteredCompanyList(p -> false);

        assertTrue(model.getFilteredCompanyList().isEmpty());
    }
}
