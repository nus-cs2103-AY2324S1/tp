package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.exceptions.DuplicateCustomerException;
import seedu.address.testutil.CustomerBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateCustomers_throwsDuplicateCustomerException() {
        // Two customers with the same identity fields

        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();

        List<Customer> newCustomers = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newCustomers);

        assertThrows(DuplicateCustomerException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasCustomer_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasCustomer(null));
    }

    @Test
    public void hasCustomer_customerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasCustomer(ALICE));
    }

    @Test
    public void hasCustomer_customerInAddressBook_returnsTrue() {
        addressBook.addCustomer(ALICE);
        assertTrue(addressBook.hasCustomer(ALICE));
    }

    @Test
    public void hasCustomer_customerWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addCustomer(ALICE);

        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();

        assertTrue(addressBook.hasCustomer(editedAlice));
    }

    @Test
    public void hasCustomerWithSamePhone_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasCustomerWithSamePhone(null));
    }

    @Test
    public void hasCustomerWithSamePhone_customerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasCustomerWithSamePhone(ALICE));
    }

    @Test
    public void hasCustomerWithSamePhone_customerInAddressBook_returnsTrue() {
        addressBook.addCustomer(ALICE);
        assertTrue(addressBook.hasCustomerWithSamePhone(ALICE));
    }

    @Test
    public void hasCustomerWithSamePhone_customerWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addCustomer(ALICE);
        Customer editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(addressBook.hasCustomerWithSamePhone(editedAlice));
    }

    @Test
    public void hasCustomerWithSamePhone_customerWithSamePhoneFieldInAddressBook_returnsTrue() {
        addressBook.addCustomer(ALICE);
        Customer editedAlice = new CustomerBuilder(ALICE).withCustomerId(101).withName(VALID_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(addressBook.hasCustomerWithSamePhone(editedAlice));
    }

    @Test
    public void getCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{customers=" + addressBook.getList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyBook whose customers list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyBook<Customer> {
        private final ObservableList<Customer> customers = FXCollections.observableArrayList();

        AddressBookStub(Collection<Customer> customers) {
            this.customers.setAll(customers);
        }

        @Override
        public ObservableList<Customer> getList() {
            return customers;
        }

        @Override
        public Optional<Customer> getById(int id) {
            return Optional.empty();
        }
    }

}
