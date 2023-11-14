package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Customer;
/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCustomerCommand}.
 */
public class DeleteCustomerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Customer customerToDelete = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        DeleteCustomerCommand delcustCommand = new DeleteCustomerCommand(INDEX_FIRST_CUSTOMER);

        String expectedMessage = String.format(DeleteCustomerCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS,
                Messages.format(customerToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getPropertyBook(), new UserPrefs());
        expectedModel.deleteCustomer(customerToDelete);

        assertCommandSuccess(delcustCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCustomerList().size() + 1);
        DeleteCustomerCommand delcustCommand = new DeleteCustomerCommand(outOfBoundIndex);

        assertCommandFailure(delcustCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        Customer customerToDelete = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        DeleteCustomerCommand delcustCommand = new DeleteCustomerCommand(INDEX_FIRST_CUSTOMER);

        String expectedMessage = String.format(DeleteCustomerCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS,
                Messages.format(customerToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getPropertyBook(), new UserPrefs());
        expectedModel.deleteCustomer(customerToDelete);
        showNoCustomer(expectedModel);

        assertCommandSuccess(delcustCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        Index outOfBoundIndex = INDEX_SECOND_CUSTOMER;
        // ensures that outOfBoundIndex is still in bounds of budget book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCustomerList().size());

        DeleteCustomerCommand delcustCommand = new DeleteCustomerCommand(outOfBoundIndex);

        assertCommandFailure(delcustCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCustomerCommand delcustFirstCommand = new DeleteCustomerCommand(INDEX_FIRST_CUSTOMER);
        DeleteCustomerCommand delcustSecondCommand = new DeleteCustomerCommand(INDEX_SECOND_CUSTOMER);

        // same object -> returns true
        assertTrue(delcustFirstCommand.equals(delcustFirstCommand));

        // same values -> returns true
        DeleteCustomerCommand deleteFirstCommandCopy = new DeleteCustomerCommand(INDEX_FIRST_CUSTOMER);
        assertTrue(delcustFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(delcustFirstCommand.equals(1));

        // null -> returns false
        assertFalse(delcustFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(delcustFirstCommand.equals(delcustSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCustomerCommand delcustCommand = new DeleteCustomerCommand(targetIndex);
        String expected = DeleteCustomerCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, delcustCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCustomer(Model model) {
        model.updateFilteredCustomerList(p -> false);

        assertTrue(model.getFilteredCustomerList().isEmpty());
    }
}
