package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.FilterSettings;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.predicate.SerializablePredicate;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBookManager addressBookManager;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBookManager = new AddressBookManager();
        this.userPrefs = new UserPrefs(userPrefs);

        if (addressBookManager.getActiveAddressBook() == null) {
            filteredPersons = new FilteredList<>(new UniquePersonList().asUnmodifiableObservableList());
        } else {
             filteredPersons = new FilteredList<>(this.addressBookManager.getActiveAddressBook().getPersonList());
            filteredPersons.setPredicate(this.userPrefs.getFilterSettings().getComposedFilter());
        }
    }

    public ModelManager() {
        this(new AddressBook("TEST"), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public FilterSettings getFilterSettings() {
        return userPrefs.getFilterSettings();
    }

    @Override
    public void setFilterSettings(FilterSettings filterSettings) {
        requireNonNull(filterSettings);
        userPrefs.setFilterSettings(filterSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        addressBookManager.setAddressBook(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBookManager.getActiveAddressBook();
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBookManager.getActiveAddressBook().hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBookManager.getActiveAddressBook().removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        requireNonNull(person);
        addressBookManager.getActiveAddressBook().addPerson(person);
        clearFilters();
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBookManager.getActiveAddressBook().setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void addFilter(SerializablePredicate predicate) {
        requireNonNull(predicate);
        HashSet<SerializablePredicate> currentFilters = this.getFilterSettings().getFilters();
        currentFilters.add(predicate);
        this.setFilterSettings(new FilterSettings(currentFilters));
        filteredPersons.setPredicate(this.getFilterSettings().getComposedFilter());
    }

    @Override
    public void deleteFilter(SerializablePredicate predicate) {
        requireNonNull(predicate);
        HashSet<SerializablePredicate> currentFilters = this.getFilterSettings().getFilters();
        currentFilters.remove(predicate);
        this.setFilterSettings(new FilterSettings(currentFilters));
        filteredPersons.setPredicate(this.getFilterSettings().getComposedFilter());
    }

    @Override
    public void clearFilters() {
        this.setFilterSettings(new FilterSettings());
        filteredPersons.setPredicate(this.getFilterSettings().getComposedFilter());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBookManager.equals(otherModelManager.addressBookManager)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
