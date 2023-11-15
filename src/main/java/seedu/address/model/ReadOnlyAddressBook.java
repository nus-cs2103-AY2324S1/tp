package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.stall.Stall;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the stalls list.
     * This list will not contain any duplicate stalls.
     */
    ObservableList<Stall> getStallList();

}
