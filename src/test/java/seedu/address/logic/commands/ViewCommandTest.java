package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewCommand}.
 */
public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Company companyToView = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_COMPANY);

        String companyDetailsToDisplayString = companyToView.getName().toString();

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_COMPANY_SUCCESS,
                companyDetailsToDisplayString);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setCurrentViewedCompany(companyToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Index outOfBoundIndex = INDEX_SECOND_COMPANY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCompanyList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_COMPANY);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_COMPANY);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_COMPANY);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different company -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewCommand viewCommand = new ViewCommand(targetIndex);
        String expected = ViewCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, viewCommand.toString());
    }
}
