package seedu.address.model;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Person;

/**
 * Represents commited frames of ModelManager
 */
public class ReadOnlyModelManager {
    public final AddressBook addressBook;
    public final FilteredList<Person> filteredPersons;
    public final UserPrefs userPrefs;
    public final Person selectedPerson;

    /**
     * Constructor
     * @param addressBook
     * @param filteredPersons
     * @param userPrefs
     * @param selectedPerson
     */
    public ReadOnlyModelManager(AddressBook addressBook, FilteredList filteredPersons,
                                 UserPrefs userPrefs, Person selectedPerson) {
        this.addressBook = addressBook;
        this.filteredPersons = filteredPersons;
        this.userPrefs = userPrefs;
        this.selectedPerson = selectedPerson;
     }
}
