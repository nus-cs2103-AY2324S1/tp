package seedu.address.model;

import java.nio.file.Path;

import javafx.beans.value.ObservableStringValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.FilterSettings;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.predicate.SerializablePredicate;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' filter settings.
     */
    FilterSettings getFilterSettings();

    /**
     * Sets the user prefs' filter settings.
     */
    void setFilterSettings(FilterSettings filterSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Returns the AddressBookManager */
    ReadOnlyAddressBookManager getAddressBookManager();

    /**
     * Adds the given address book.
     * {@code addressBook} must not already exist in the address book manager.
     */
    void addAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Deletes the given address book.
     * {@code addressBook} must exist in the address book manager.
     */
    void deleteAddressBook(String courseCode);

    /**
     * Sets the active address book.
     * {@code courseCode} address book must exist in the address book manager.
     */
    void setActiveAddressBook(String courseCode);

    /**
     * Returns true if an address book with the given {@code courseCode} exists.
     */
    boolean hasAddressBook(String courseCode);

    /**
     * Returns a view of the address book list.
     */
    ObservableList<String> getCourseList();

    /**
     * Returns a observable string value of the active course code.
     */
    ObservableStringValue getObservableCourseCode();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the unfiltered person list */
    ObservableList<Person> getUnfilteredPersonList();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Adds the given predicate to filter list.
     * {@code predicate} must not already exist in the address book.
     */
    void addFilter(SerializablePredicate predicate);

    /**
     * Deletes the given predicate.
     * The predicate must exist in the filter list.
     */
    void deleteFilter(SerializablePredicate predicate);

    /**
     * Clears all filters.
     */
    void clearFilters();
}
