package seedu.address.model.consultation;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of a Consultation.
 */
public interface ReadOnlyConsultation {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
