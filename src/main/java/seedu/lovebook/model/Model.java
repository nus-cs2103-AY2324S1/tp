package seedu.lovebook.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.lovebook.commons.core.GuiSettings;
import seedu.lovebook.model.person.Date;

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
     * Returns the user prefs' lovebook book file path.
     */
    Path getLoveBookFilePath();

    /**
     * Sets the user prefs' lovebook book file path.
     */
    void setLoveBookFilePath(Path loveBookFilePath);

    /**
     * Replaces lovebook book data with the data in {@code LoveBook}.
     */
    void setLoveBook(ReadOnlyLoveBook loveBook);

    /** Returns the LoveBook */
    ReadOnlyLoveBook getLoveBook();

    /**
     * Returns true if a date with the same identity as {@code date} exists in the lovebook book.
     */
    boolean hasPerson(Date date);

    /**
     * Deletes the given date.
     * The date must exist in the lovebook book.
     */
    void deletePerson(Date target);

    /**
     * Adds the given date.
     * {@code date} must not already exist in the lovebook book.
     */
    void addPerson(Date date);

    /**
     * Replaces the given date {@code target} with {@code editedDate}.
     * {@code target} must exist in the lovebook book.
     * The date identity of {@code editedDate} must not be the same as another existing date in the lovebook book.
     */
    void setPerson(Date target, Date editedDate);

    /** Returns an unmodifiable view of the filtered date list */
    ObservableList<Date> getFilteredPersonList();

    /**
     * Updates the filter of the filtered date list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Date> predicate);

    /**
     * Greets the user with a welcome message upon initial launch of app.
     * Returns a String of welcome message.
     */
    String getWelcomeMessage();

    Path getDatePrefsFilePath();

    void setDatePrefsFilePath(Path datePrefsFilePath);

    void setDatePrefs(ReadOnlyDatePrefs datePrefs);

    ReadOnlyDatePrefs getDatePrefs();
}
