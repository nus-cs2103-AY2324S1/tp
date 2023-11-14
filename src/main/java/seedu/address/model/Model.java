package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventID;
import seedu.address.model.person.ContactID;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /**
     * Generate a new list from sorting the filtered event list by the given {@code comparator}
     * and return the generated list.
     * @throws NullPointerException if {@code comparator} is null.
     */
    List<Event> generateSortedFilteredEventList(Comparator<? super Event> comparator);

    /**
     * Return the sorted-filtered-list generated
     * by {@code generateSortedFilteredEventList(Comparator<? super Event> comparator)}
     * @throws NullPointerException if {@code sorted-filtered-list} is null
     *     (never called {@code generateSortedFilteredEventList(Comparator<? super Event> comparator)} before).
     */
    ObservableList<Event> getSortedFilteredEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);


    /**
     * Find a person by index.
     * If the index is invalid, returns null.
     */
    Person findPersonByIndex(int index);

    /**
     * Find a person by the user-friendly ID displayed in the person card on the UI.
     * If the ID is invalid, returns null.
     */
    Person findPersonByUserFriendlyId(ContactID id);

    /**
     * Add an event under the event list of the specified person and also the global event list
     */
    void addEvent(Event toAdd, Person owner);

    /**
     * Remove an event by its user-friendly id for the specified person and also in the global event list
     * @param eventID The id of the event you want to remove
     * @return The event object that is just deleted if the operation is successful
     *     or {@code null} if the event with this name does not exist
     */
    Event removeEventByID(EventID eventID, Person owner);

    /**
     * Convert the filtered event list to a human-readable string
     * @return The filtered event list as string
     */
    String filteredEventListToString();
}
