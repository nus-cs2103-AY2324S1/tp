package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ArrayList<Event> getEventsList();

}
