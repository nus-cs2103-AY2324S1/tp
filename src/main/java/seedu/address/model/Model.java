package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.user.ReadOnlyUserData;
import seedu.address.model.user.ReadOnlyUserPrefs;
import seedu.address.model.user.User;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<Person> PREDICATE_SHOW_USER = user -> true;

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
     * Replaces user data with the data in {@code userData}.
     */
    void setUserData(ReadOnlyUserData userData);

    /**
     * Returns the user data.
     */
    ReadOnlyUserData getUserData();

    /**
     * Returns the user.
     */
    User getUser();

    /**
     * Sets the user.
     * @param user
     */
    void setUser(User user);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with the same phone number as {@code person} exists in the address book.
     */
    boolean hasPhone(Person person);

    /**
     * Returns true if a person with the same email address as {@code person} exists in the address book.
     */
    boolean hasEmail(Person person);

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

    String getBirthdayList();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the user */
    ObservableList<User> getUserView();

    /**
     * Returns the person in the address book with the given name.
     * The name must be an exact match (case-insensitive).
     *
     * @param name The name of the person to retrieve.
     * @return The person with the given name, or null if no such person exists.
     */
    Person getPersonWithName(Name name);

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
