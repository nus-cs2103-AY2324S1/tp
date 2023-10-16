package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Convert the event list to a human-readable string
     * @return The event list as string
     */
    String eventListToString();

    /**
     * Convert the note list to a human-readable string
     * @return The note list as string
     */
    String noteListToString();

}
