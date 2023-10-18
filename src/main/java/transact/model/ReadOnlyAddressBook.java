package transact.model;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import transact.model.person.Person;
import transact.model.person.PersonId;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list converted from the map.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the person map.
     */
    ObservableMap<PersonId, Person> getPersonMap();

}
