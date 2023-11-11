package seedu.lovebook.model;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.lovebook.commons.core.GuiSettings;
import seedu.lovebook.commons.core.LogsCenter;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.date.Date;
import seedu.lovebook.model.date.RandomPredicate;

/**
 * Represents the in-memory model of the LoveBook data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final LoveBook loveBook;
    private final UserPrefs userPrefs;
    private final DatePrefs datePrefs;
    private final FilteredList<Date> filteredDates;
    private SortedList<Date> sortedList;

    /**
     * Initializes a ModelManager with the given LoveBook and userPrefs.
     */
    public ModelManager(ReadOnlyLoveBook loveBook, ReadOnlyUserPrefs userPrefs, ReadOnlyDatePrefs datePrefs) {
        requireAllNonNull(loveBook, userPrefs, datePrefs);

        logger.fine("Initializing with LoveBook: " + loveBook + " and user prefs " + userPrefs
                + " and date prefs " + datePrefs);

        this.loveBook = new LoveBook(loveBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.datePrefs = new DatePrefs(datePrefs);
        filteredDates = new FilteredList<>(this.loveBook.getPersonList());
        sortedList = new SortedList<>(filteredDates);
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
    public boolean hasDate(Date date) {
        requireNonNull(date);
        return loveBook.hasDate(date);
    }

    @Override
    public void deleteDate(Date target) {
        loveBook.removePerson(target);
    }

    @Override
    public void addDate(Date date) {
        loveBook.addDate(date);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setDate(Date target, Date editedDate) {
        requireAllNonNull(target, editedDate);

        loveBook.setDate(target, editedDate);
    }

    //=========== Filtered Date List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Date} backed by the internal list of
     * {@code versionedLoveBook}
     */
    @Override
    public ObservableList<Date> getFilteredPersonList() {
        return sortedList;
    }

    /**
     * Returns a random date from the date list.
     */
    public void getRandomDate() throws CommandException {
        if (loveBook.getPersonList().size() == 0) {
            throw new CommandException("Initialise list with dates before calling blindDates");
        }
        Random randomGenerator = new Random();
        int randomIndex = randomGenerator.nextInt(loveBook.getPersonList().size());
        Date person = loveBook.getPersonList().get(randomIndex);
        updateFilteredPersonList(new RandomPredicate(person));
    }

    @Override
    public void updateSortedPersonList(Comparator<Date> comparator) {
        requireNonNull(comparator);
        sortedList.setComparator(comparator);
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
        return this.datePrefs.getPreferences().get(0);
    }


    @Override
    public Path getDatePrefsFilePath() {
        return this.userPrefs.getDatePrefsFilePath();
    }

    @Override
    public void setDatePrefsFilePath(Path datePrefsFilePath) {
        this.userPrefs.setDatePrefsFilePath(datePrefsFilePath);
    }

    @Override
    public void getBestDate() throws CommandException {
        if (loveBook.getPersonList().size() == 0) {
            throw new CommandException("Initialise list with dates before calling bestMatch");
        }
        ObservableList<Date> dateList = loveBook.getPersonList();
        Date bestDate = dateList.stream().max(Comparator.comparing(date -> date.getScore(this.datePrefs))).orElse(null);
        filteredDates.setPredicate(date -> date.equals(bestDate));
    }

}
