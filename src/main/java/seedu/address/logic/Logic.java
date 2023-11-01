package seedu.address.logic;

import java.nio.file.Path;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ShortcutSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.Theme;
import seedu.address.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns a person that is selected*/
    Person getSelectedPerson();

    /** Update the selected person*/
    void updateSelectedPerson(Person person);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
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

}
