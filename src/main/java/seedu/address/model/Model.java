package seedu.address.model;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

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

    /** Returns an unmodifiable view of the full person list */
    ObservableList<Person> getFullPersonList();

    /** Returns a view of the event list */
    ObservableList<Event> getFilteredEventList();

    /**
     * Replaces the given person {@code target} with {@code editedPerson} in the address book
     * @param target event to be edited. {@code target} must exist in the address book.
     * @param editedEvent event with the edited details.
     */
    void setEvent(Event target, Event editedEvent);

    /**
     * Deletes the given event.
     * @param target event to be deleted. {@code target} must exist in the address book.
     */
    void deleteEvent(Event target);

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    /**
     * Adds an event to the list of events.
     * @param toAdd Event to be added.
     */
    void addEvent(Event toAdd);

    /**
     * Returns a set of names that are not found in the address book.
     * @param names Set of names to be checked.
     * @return Set of names that are not found in the address book.
     */
    Set<Name> findInvalidNames(Set<Name> names);

    /**
     * Returns a set of groups that are not found in the address book.
     * @param groups Set of groups to be checked.
     * @return Set of groups that are not found in the address book.
     */
    Set<Group> findInvalidGroups(Set<Group> groups);

    Set<Group> getEmptyGroups(Person person);

    void removeEmptyGroups(Set<Group> emptyGroups);

    void updateGroups();

    /**
     * Updates any events where the person to edit is assigned to.
     * @param personToEdit person to edit
     * @param editedPerson person with the edited details
     */
    void updateAssignedPersons(Person personToEdit, Person editedPerson);

    /**
     * Overloaded method to update any events where the person to delete is assigned to.
     * @param personToDelete person to delete
     */
    void updateAssignedPersons(Person personToDelete);


}
