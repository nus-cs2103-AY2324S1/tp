package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.statistics.ReadOnlySummaryStatistic;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<Tag> PREDICATE_SHOW_ALL_TAGS = unused -> true;

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

    Path getEventBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    void setEventBookFilePath(Path eventBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    void setEventBook(ReadOnlyEventBook eventBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    ReadOnlyEventBook getEventBook();

    ReadOnlySummaryStatistic getSummaryStatistic();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    boolean hasEvent(Event event);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    void deleteEvent(Event event);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);
    void addEvent(Event event);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);
    void setEvent(Event target, Event editedEvent);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();
    ObservableList<Tag> getFilteredTagList();
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered person list to filter by all the given {@code predicates}.
     * @throws NullPointerException if any of the {@code predicatesList} is null.
     */
    void updateFilteredPersonList(List<Predicate<Person>> predicatesList);

    void updateFilteredTagList(Predicate<Tag> predicate);

    void updateFilteredEventList(Predicate<Event> predicate);

    void updateFilteredEventList(List<Predicate<Event>> predicateList);

    /**
     * Sorts the list of persons using the provided comparator.
     *
     * @param comparator The comparator used to determine the sorting order.
     */
    void sortPersonList(Comparator<Person> comparator);

    void sortEventList(Comparator<Event> comparator);


    /**
     * Returns the Index of the last view command called.
     */
    Index getLastViewedPersonIndex();

    /**
     * Sets the Index of the last view command called.
     */
    void setLastViewedPersonIndex(Index index);


    void addTag(Tag tag) throws IllegalValueException;

    boolean hasTag(Tag tag);
    /**
     * Loads the summary statistics based on the current Address Book
     */
    void loadSummaryStatistics();


}
