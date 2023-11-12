package seedu.address.model;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ThemeProperty;
import seedu.address.model.person.Person;

/**
 * Represents commited frames of ModelManager
 */
public class ReadOnlyModelManager {
    public final AddressBook addressBook;
    public final FilteredList<Person> filteredPersons;
    public final UserPrefs userPrefs;
    public final Person selectedPerson;
    public final ThemeProperty themeProperty;

    /**
     * Constructor
     * @param addressBook
     * @param filteredPersons
     * @param userPrefs
     * @param selectedPerson
     * @param themeProperty
     */
    public ReadOnlyModelManager(AddressBook addressBook, FilteredList<Person> filteredPersons,
                                 UserPrefs userPrefs, Person selectedPerson, ThemeProperty themeProperty) {
        this.addressBook = addressBook;
        this.filteredPersons = filteredPersons;
        this.userPrefs = userPrefs;
        this.selectedPerson = selectedPerson;
        this.themeProperty = themeProperty;
    }
}
