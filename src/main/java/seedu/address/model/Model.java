package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ShortcutSettings;
import seedu.address.logic.commands.CommandWord;
import seedu.address.logic.commands.ShortcutAlias;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluates to true */
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
     * Returns the user prefs' shortcut settings.
     */
    ShortcutSettings getShortcutSettings();

    /**
     * Sets the user prefs' shortcut settings.
     */
    void setShortcutSettings(ShortcutSettings shortcutSettings);
    /**
     * Registers a new shortcut mapping.
     */
    String registerShortcut(ShortcutAlias shortcutAlias, CommandWord commandWord);
    /**
     * Removes the shortcut mapping.
     */
    String removeShortcut(ShortcutAlias shortcutAlias);
    /**
     * Checks if the alias has a mapping registered.
     */
    String getShortcut(String alias);
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

    Person getSelectedPerson();

    void updateSelectedPerson(Person person);

    public boolean isSelectedEmpty();
    /**
     * Returns the command string of the next most recent command executed.
     */
    String getPrevCommandString(String currentCommandString);

    /**
     * Returns the command string of the previous most recent command executed.
     */
    String getPassedCommandString(String currentCommandString);

    /**
     * Adds the most recent command string input by the user to the CommandStringStash.
     */
    void addCommandString(String commandString);

    /**
     * Sets the current theme of the application to be {@code theme}
     */
    void setTheme(Theme theme);

    /**
     * Adds @code{changeListener} as an observer to the application theme.
     */
    void addThemeListener(ChangeListener<? super Theme> changeListener);

    boolean hasHistory();
    boolean canRedo();
    void undo();
    void redo();
    void commit();
}
