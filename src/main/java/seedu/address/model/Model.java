package seedu.address.model;

import static seedu.address.logic.parser.CliSyntax.FIELD_HIDDEN;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.ListPredicate;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that filters for all hidden persons
     */
    Predicate<Person> PREDICATE_SHOW_ALL_HIDDEN_PERSONS = new ListPredicate(FIELD_HIDDEN, true);
    /**
     * {@code Predicate} that filters for all unhidden persons
     */
    Predicate<Person> PREDICATE_SHOW_ALL_UNHIDDEN_PERSONS = new ListPredicate(FIELD_HIDDEN, false);

    /**
     * {@code Predicate} that filters for all bookmarked applicants
     */
    Predicate<Person> PREDICATE_SHOW_ALL_BOOKMARKED_PERSONS = new ListPredicate(FIELD_HIDDEN, true);

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

    /**
     * Displays the given person.
     * The person must exist in the address book.
     */
    void showPersonAtIndex(Index index);

    /**
     * CLears details of person displayed in detail view of UI.
     */
    void clearPersonDetails();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the sorted person list to sort by the given {@code comparator}.
     *  @throws NullPointerException if {@code predicate} is null.
     */
    void sortFilteredPersonList(Comparator<Person> comparator);

    /** Returns the current person being viewed in detail */
    ObservableValue<Optional<Person>> getCurrentPerson();
}
