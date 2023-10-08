package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Date;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Date> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns true if a date with the same identity as {@code date} exists in the address book.
     */
    boolean hasPerson(Date date);

    /**
     * Deletes the given date.
     * The date must exist in the address book.
     */
    void deletePerson(Date target);

    /**
     * Adds the given date.
     * {@code date} must not already exist in the address book.
     */
    void addPerson(Date date);

    /**
     * Replaces the given date {@code target} with {@code editedDate}.
     * {@code target} must exist in the address book.
     * The date identity of {@code editedDate} must not be the same as another existing date in the address book.
     */
    void setPerson(Date target, Date editedDate);

    /** Returns an unmodifiable view of the filtered date list */
    ObservableList<Date> getFilteredPersonList();

    /**
     * Updates the filter of the filtered date list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Date> predicate);
}
