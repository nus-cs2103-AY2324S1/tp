package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;
import seedu.address.model.company.Remark;
import seedu.address.testutil.CompanyBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private static final String REMARK_STUB = "No remarks"; //default remark after delete

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Company firstCompany = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = new CompanyBuilder(firstCompany).withRemark(VALID_REMARK_GOOGLE).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_COMPANY,
                new Remark(editedCompany.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                editedCompany.getName().fullName);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCompany(firstCompany, editedCompany);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeRemarkUnfilteredList_success() {
        Company firstCompany = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = new CompanyBuilder(firstCompany).withRemark(REMARK_STUB).build();

        Remark remark = new Remark(REMARK_STUB);
        remark.deleteRemark();
        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_COMPANY, remark);

        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS,
                editedCompany.getName().fullName);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCompany(firstCompany, editedCompany);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addRemarkFilteredList_success() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Company firstCompany = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = new CompanyBuilder(model.getFilteredCompanyList()
                .get(INDEX_FIRST_COMPANY.getZeroBased())).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_COMPANY,
                new Remark(editedCompany.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                editedCompany.getName().fullName);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showCompanyAtIndex(expectedModel, INDEX_FIRST_COMPANY);
        expectedModel.setCompany(firstCompany, editedCompany);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeRemarkFilteredList_success() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Company firstCompany = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = new CompanyBuilder(model.getFilteredCompanyList()
                .get(INDEX_FIRST_COMPANY.getZeroBased())).withRemark(REMARK_STUB).build();

        Remark remark = new Remark(REMARK_STUB);
        remark.deleteRemark();
        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_COMPANY, remark);

        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS,
                editedCompany.getName().fullName);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showCompanyAtIndex(expectedModel, INDEX_FIRST_COMPANY);
        expectedModel.setCompany(firstCompany, editedCompany);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCompanyIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_TIKTOK));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidCompanyIndexFilteredList_failure() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);
        Index outOfBoundIndex = INDEX_SECOND_COMPANY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCompanyList().size());

        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_TIKTOK));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_COMPANY,
                new Remark(VALID_REMARK_GOOGLE));

        // same values -> returns true
        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_COMPANY,
                new Remark(VALID_REMARK_GOOGLE));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_SECOND_COMPANY,
                new Remark(VALID_REMARK_GOOGLE))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_FIRST_COMPANY,
                new Remark(VALID_REMARK_TIKTOK))));
    }
}
