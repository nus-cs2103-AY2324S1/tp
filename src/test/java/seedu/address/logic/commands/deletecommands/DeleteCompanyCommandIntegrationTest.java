package seedu.address.logic.commands.deletecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertPersonCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCompanyCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Company companyToDelete = model.getFilteredCompanyList()
                .get(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY.getZeroBased());
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY);

        String expectedMessage = String.format(DeleteCompanyCommand.MESSAGE_SUCCESS,
                Messages.formatCompany(companyToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteCompany(companyToDelete);

        CommandTestUtil.assertRegularCommandSuccess(deleteCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(outOfBoundIndex);

        assertPersonCommandFailure(deleteCompanyCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCompanyAtIndex(model, INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY);

        Company companyToDelete = model.getFilteredCompanyList()
                .get(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY.getZeroBased());
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY);

        String expectedMessage = String.format(DeleteCompanyCommand.MESSAGE_SUCCESS,
                Messages.formatCompany(companyToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteCompany(companyToDelete);
        showNoCompany(expectedModel);

        CommandTestUtil.assertRegularCommandSuccess(deleteCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCompanyAtIndex(model, INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY);

        Index outOfBoundIndex = INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCompanyList().size());

        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(outOfBoundIndex);

        assertPersonCommandFailure(deleteCompanyCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCompanyCommand deleteFirstCommand = new DeleteCompanyCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY);
        DeleteCompanyCommand deleteSecondCommand = new DeleteCompanyCommand(INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCompanyCommand deleteFirstCommandCopy =
                new DeleteCompanyCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different Company -> returns false
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

