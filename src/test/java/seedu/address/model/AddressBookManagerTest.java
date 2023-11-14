package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class AddressBookManagerTest {
    @Test
    public void equals() {
        HashMap<String, ReadOnlyAddressBook> firstAddressBookMap = new HashMap<>();
        firstAddressBookMap.put("CS2101", new AddressBook("CS2101"));

        AddressBookManager firstAddressBookManager = new AddressBookManager(new HashMap<>(), "CS2101");
        AddressBookManager secondAddressBookManager = new AddressBookManager(firstAddressBookMap, "CS2101");

        // same object -> returns true
        assertTrue(firstAddressBookManager.equals(firstAddressBookManager));

        // same values -> returns true
        AddressBookManager firstAddressBookMangerCopy = new AddressBookManager(new HashMap<>(), "CS2101");
        assertTrue(firstAddressBookManager.equals(firstAddressBookMangerCopy));

        // different types -> returns false
        assertFalse(firstAddressBookManager.equals(1));

        // null -> returns false
        assertFalse(firstAddressBookManager.equals(null));

        // different address books -> returns false
        assertFalse(firstAddressBookManager.equals(secondAddressBookManager));
    }
}
