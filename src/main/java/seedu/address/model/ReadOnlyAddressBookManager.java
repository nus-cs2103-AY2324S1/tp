package seedu.address.model;

import java.util.HashMap;

/**
 * Unmodifiable view of an address book manager
 */
public interface ReadOnlyAddressBookManager {

    /**
     * Returns an unmodifiable view of the address books hashmap.
     * This list will not contain any duplicate address books.
     */
    HashMap<String, ReadOnlyAddressBook> getAddressBooks();

    /**
     * Returns the course code of the active address book.
     */
    String getActiveCourseCode();

}
