package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Musician> getMusicianList();


    ObservableList<Band> getBandList();

}
