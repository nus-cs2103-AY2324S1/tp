package seedu.lovebook.model;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Random;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.lovebook.commons.core.GuiSettings;
import seedu.lovebook.commons.core.LogsCenter;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.model.person.RandomPredicate;

/**
 * Represents the in-memory model of the lovebook book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final LoveBook loveBook;
    private final UserPrefs userPrefs;
    private final DatePrefs datePrefs;
    private final FilteredList<Date> filteredDates;

    /**
     * Initializes a ModelManager with the given LoveBook and userPrefs.
     */
    public ModelManager(ReadOnlyLoveBook loveBook, ReadOnlyUserPrefs userPrefs, ReadOnlyDatePrefs datePrefs) {
        requireAllNonNull(loveBook, userPrefs, datePrefs);

        logger.fine("Initializing with lovebook book: " + loveBook + " and user prefs " + userPrefs
                + " and date prefs " + datePrefs);

        this.loveBook = new LoveBook(loveBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.datePrefs = new DatePrefs(datePrefs);
        filteredDates = new FilteredList<>(this.loveBook.getPersonList());
    }

    public ModelManager() {
        this(new LoveBook(), new UserPrefs(), new DatePrefs());
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
    public void setLoveBookFilePath(Path loveBookFilePath) {
        requireNonNull(loveBookFilePath);
        userPrefs.setLoveBookFilePath(loveBookFilePath);
    }

    //=========== LoveBook ================================================================================

    @Override
    public void setLoveBook(ReadOnlyLoveBook loveBook) {
        this.loveBook.resetData(loveBook);
    }

    @Override
    public ReadOnlyLoveBook getLoveBook() {
        return loveBook;
    }

    @Override
    public boolean hasPerson(Date date) {
        requireNonNull(date);
        return loveBook.hasPerson(date);
    }

    @Override
    public void deletePerson(Date target) {
        loveBook.removePerson(target);
    }

    @Override
    public void addPerson(Date date) {
        loveBook.addPerson(date);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Date target, Date editedDate) {
        requireAllNonNull(target, editedDate);

        loveBook.setPerson(target, editedDate);
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

    /**
     * Returns a random date from the date list.
     */
    public void getRandomPerson() {
        Random randomGenerator = new Random();
        int randomIndex = randomGenerator.nextInt(loveBook.getPersonList().size());
        Date person = loveBook.getPersonList().get(randomIndex);
        updateFilteredPersonList(new RandomPredicate(person));

    }

    @Override
    public void updateFilteredPersonList(Predicate<Date> predicate) {
        requireNonNull(predicate);
        filteredDates.setPredicate(predicate);
    }

    @Override
    public String getWelcomeMessage() {
        return Messages.WELCOME_MESSAGE;
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
        return loveBook.equals(otherModelManager.loveBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredDates.equals(otherModelManager.filteredDates);
    }

    //=========== DatePrefs ==================================================================================
    @Override
    public void setDatePrefs(ReadOnlyDatePrefs prefs) {
        this.datePrefs.resetData(prefs);
    }

    @Override
    public ReadOnlyDatePrefs getDatePrefs() {
        return this.datePrefs.getPreferences();
    }

    @Override
    public Path getDatePrefsFilePath() {
        return this.userPrefs.getDatePrefsFilePath();
    }

    @Override
    public void setDatePrefsFilePath(Path datePrefsFilePath) {
        this.userPrefs.setDatePrefsFilePath(datePrefsFilePath);
    }

}
