package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.DESC_COMPANY_A;
import static connectify.logic.commands.CommandTestUtil.DESC_COMPANY_B;
import static connectify.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_B;
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
import connectify.logic.commands.EditCompanyCommand.EditCompanyDescriptor;
import connectify.model.AddressBook;
import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.company.Company;
import connectify.testutil.CompanyBuilder;
import connectify.testutil.EditCompanyDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCompanyCommand.
 */
public class EditCompanyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Company editedCompany = new CompanyBuilder().build();
        Company expectedCompany = model.getFilteredCompanyList().get(0);

        // copy over people
        for (int i = 0; i < expectedCompany.getPersonList().size(); i++) {
            editedCompany = editedCompany.addPersonToCompany(expectedCompany.getPersonList().get(i));
        }
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(editedCompany).build();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(INDEX_FIRST_COMPANY, descriptor);

        String expectedMessage = String.format(EditCompanyCommand.MESSAGE_SUCCESS,
                Messages.format(editedCompany));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCompany(model.getFilteredCompanyList().get(0), editedCompany);

        assertCommandSuccess(editCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastCompany = Index.fromOneBased(model.getFilteredCompanyList().size());
        Company lastCompany = model.getFilteredCompanyList().get(indexLastCompany.getZeroBased());

        CompanyBuilder companyInList = new CompanyBuilder(lastCompany);
        Company editedCompany = companyInList.withName(VALID_COMPANY_NAME_B).build();

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_NAME_B).build();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(indexLastCompany, descriptor);

        String expectedMessage = String.format(EditCompanyCommand.MESSAGE_SUCCESS,
                Messages.format(editedCompany));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCompany(lastCompany, editedCompany);

        assertCommandSuccess(editCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(INDEX_FIRST_COMPANY,
                new EditCompanyDescriptor());
        Company editedCompany = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());

        String expectedMessage = String.format(EditCompanyCommand.MESSAGE_SUCCESS,
                Messages.format(editedCompany));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Company companyInFilteredList = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = new CompanyBuilder(companyInFilteredList).withName(VALID_COMPANY_NAME_B).build();
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_NAME_B).build();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(INDEX_FIRST_COMPANY, descriptor);

        String expectedMessage = String.format(EditCompanyCommand.MESSAGE_SUCCESS,
                Messages.format(editedCompany));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCompany(model.getFilteredCompanyList().get(0), editedCompany);

        assertCommandSuccess(editCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateCompanyUnfilteredList_failure() {
        Company firstCompany = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(firstCompany).build();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(INDEX_SECOND_COMPANY, descriptor);

        assertCommandFailure(editCompanyCommand, model, EditCompanyCommand.MESSAGE_DUPLICATE_COMPANY);
    }

    @Test
    public void execute_invalidIndexProvided_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_NAME_B).build();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCompanyCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCompanyCommand standardCommand = new EditCompanyCommand(INDEX_FIRST_COMPANY, DESC_COMPANY_A);

        // same values -> returns true
        EditCompanyDescriptor copyDescriptor = new EditCompanyDescriptor(DESC_COMPANY_A);
        EditCompanyCommand commandWithSameValues = new EditCompanyCommand(INDEX_FIRST_COMPANY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCompanyCommand(INDEX_SECOND_COMPANY, DESC_COMPANY_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCompanyCommand(INDEX_FIRST_COMPANY, DESC_COMPANY_B)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditCompanyDescriptor editCompanyDescriptor = new EditCompanyDescriptor();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(index, editCompanyDescriptor);
        String expected = EditCompanyCommand.class.getCanonicalName()
                + "{companyIndex=" + index + ", editCompanyDescriptor="
                + editCompanyDescriptor + "}";
        assertEquals(expected, editCompanyCommand.toString());
    }

    @Test
    public void execute_invalidCompanyIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_NAME_B).build();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCompanyCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }
}
