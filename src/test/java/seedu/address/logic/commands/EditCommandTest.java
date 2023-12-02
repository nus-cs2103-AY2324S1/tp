package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_COMPANY_SUCCESS;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;
import seedu.address.testutil.CompanyBuilder;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        Company editedCompany = new CompanyBuilder().build();
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(editedCompany).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_COMPANY, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_COMPANY_SUCCESS,
                Messages.getCompanyName(editedCompany));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCompany(model.getFilteredCompanyList().get(0), editedCompany);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Index indexLastCompany = Index.fromOneBased(model.getFilteredCompanyList().size());
        Company lastCompany = model.getFilteredCompanyList().get(indexLastCompany.getZeroBased());

        CompanyBuilder companyInList = new CompanyBuilder(lastCompany);
        Company editedCompany = companyInList.withName(VALID_NAME_TIKTOK).withPhone(VALID_PHONE_TIKTOK)
                .withPriority(VALID_PRIORITY_TIKTOK)
                .build();

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withName(VALID_NAME_TIKTOK)
                .withPhone(VALID_PHONE_TIKTOK)
                .withPriority(VALID_PRIORITY_TIKTOK)
                .build();
        EditCommand editCommand = new EditCommand(indexLastCompany, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_COMPANY_SUCCESS,
                Messages.getCompanyName(editedCompany));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCompany(lastCompany, editedCompany);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_COMPANY, new EditCompanyDescriptor());
        Company editedCompany = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_COMPANY_SUCCESS,
                Messages.getCompanyName(editedCompany));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Company companyInFilteredList = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = new CompanyBuilder(companyInFilteredList).withName(VALID_NAME_TIKTOK).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_COMPANY,
                new EditCompanyDescriptorBuilder().withName(VALID_NAME_TIKTOK).build());

        String expectedMessage = String.format(MESSAGE_EDIT_COMPANY_SUCCESS,
                Messages.getCompanyName(editedCompany));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showCompanyAtIndex(expectedModel, INDEX_FIRST_COMPANY);
        expectedModel.setCompany(model.getFilteredCompanyList().get(0), editedCompany);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    //Duplicate checks
    @Test
    public void execute_fullDuplicateCompany_failure() {
        Company firstCompany = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(firstCompany).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_COMPANY, descriptor);

        assertCommandFailure(editCommand,
                model,
                new CommandException.DuplicateException(
                        Messages.getDupErrMsgEdit(
                        firstCompany)).getMessage());
    }

    @Test
    public void execute_duplicatedNameAndRoleAndDeadline_failure() {
        Company firstCompany = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(firstCompany)
                .withEmail("gy@gmail.com")
                .withPhone("91234567")
                .withPriority("LOW")
                .withStatus("PA")
                .withRecruiterName("Gerald Yeo")
                .build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_COMPANY, descriptor);

        assertCommandFailure(editCommand,
                model,
                new CommandException.DuplicateException(
                        Messages.getDupErrMsgEdit(
                        firstCompany)).getMessage());
    }

    @Test
    public void execute_duplicatedRoleOnly_success() {
        Company firstCompany = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(firstCompany)
                .withEmail("gy@gmail.com")
                .withPhone("91234567")
                .withPriority("LOW")
                .withStatus("PA")
                .withDeadline("10-10-2021")
                .withRecruiterName("Gerald Yeo")
                .withName("Tiktok")
                .build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_COMPANY, descriptor);

        assert descriptor.getName().isPresent();
        assertCommandSuccess(editCommand,
                model,
                String.format(MESSAGE_EDIT_COMPANY_SUCCESS, descriptor.getName().get()), model);
    }

    @Test
    public void execute_duplicatedNameOnly_success() {
        Company firstCompany = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(firstCompany)
                .withEmail("gy@gmail.com")
                .withPhone("91234567")
                .withPriority("LOW")
                .withStatus("PA")
                .withDeadline("10-10-2021")
                .withRecruiterName("Gerald Yeo")
                .withRole("UI UX Designer")
                .build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_COMPANY, descriptor);

        assert descriptor.getName().isPresent();
        assertCommandSuccess(editCommand,
                model,
                String.format(MESSAGE_EDIT_COMPANY_SUCCESS, descriptor.getName().get()),
                model);
    }

    @Test
    public void execute_duplicateCompany_failure() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        // edit company in filtered list into a duplicate in address book
        Company companyInList = model.getAddressBook().getCompanyList().get(INDEX_SECOND_COMPANY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_COMPANY,
                new EditCompanyDescriptorBuilder(companyInList).build());

        assertCommandFailure(editCommand,
                model,
                new CommandException.DuplicateException(
                        Messages.getDupErrMsgEdit(
                                companyInList)).getMessage());
    }

    @Test
    public void execute_invalidCompanyIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withName(VALID_NAME_TIKTOK).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
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

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditCompanyDescriptorBuilder().withName(VALID_NAME_TIKTOK).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_COMPANY, DESC_GOOGLE);

        // same values -> returns true
        EditCompanyDescriptor copyDescriptor = new EditCompanyDescriptor(DESC_GOOGLE);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_COMPANY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_COMPANY, DESC_GOOGLE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_COMPANY, DESC_TIKTOK)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditCompanyDescriptor editCompanyDescriptor = new EditCompanyDescriptor();
        EditCommand editCommand = new EditCommand(index, editCompanyDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editCompanyDescriptor="
                + editCompanyDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
