package networkbook.model;

import javafx.collections.ObservableList;
import networkbook.model.person.Person;

/**
 * Unmodifiable view of an network book
 */
public interface ReadOnlyNetworkBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
