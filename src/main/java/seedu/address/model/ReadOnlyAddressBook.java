package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Date;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the dates list.
     * This list will not contain any duplicate dates.
     */
    ObservableList<Date> getPersonList();

}
