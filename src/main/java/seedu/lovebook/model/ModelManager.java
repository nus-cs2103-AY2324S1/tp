package seedu.lovebook.model;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.lovebook.commons.core.GuiSettings;
import seedu.lovebook.commons.core.LogsCenter;
import seedu.lovebook.model.person.Date;

/**
 * Represents the in-memory model of the lovebook book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final LoveBook LoveBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Date> filteredDates;

    /**
     * Initializes a ModelManager with the given LoveBook and userPrefs.
     */
    public ModelManager(ReadOnlyLoveBook LoveBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(LoveBook, userPrefs);

        logger.fine("Initializing with lovebook book: " + LoveBook + " and user prefs " + userPrefs);

        this.LoveBook = new LoveBook(LoveBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredDates = new FilteredList<>(this.LoveBook.getPersonList());
    }

    public ModelManager() {
        this(new LoveBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getLoveBookFilePath() {
        return userPrefs.getLoveBookFilePath();
    }

    @Override
    public void setLoveBookFilePath(Path LoveBookFilePath) {
        requireNonNull(LoveBookFilePath);
        userPrefs.setLoveBookFilePath(LoveBookFilePath);
    }

    //=========== LoveBook ================================================================================

    @Override
    public void setLoveBook(ReadOnlyLoveBook LoveBook) {
        this.LoveBook.resetData(LoveBook);
    }

    @Override
    public ReadOnlyLoveBook getLoveBook() {
        return LoveBook;
    }

    @Override
    public boolean hasPerson(Date date) {
        requireNonNull(date);
        return LoveBook.hasPerson(date);
    }

    @Override
    public void deletePerson(Date target) {
        LoveBook.removePerson(target);
    }

    @Override
    public void addPerson(Date date) {
        LoveBook.addPerson(date);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Date target, Date editedDate) {
        requireAllNonNull(target, editedDate);

        LoveBook.setPerson(target, editedDate);
    }

    //=========== Filtered Date List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Date} backed by the internal list of
     * {@code versionedLoveBook}
     */
    @Override
    public ObservableList<Date> getFilteredPersonList() {
        return filteredDates;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Date> predicate) {
        requireNonNull(predicate);
        filteredDates.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return LoveBook.equals(otherModelManager.LoveBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredDates.equals(otherModelManager.filteredDates);
    }

}
