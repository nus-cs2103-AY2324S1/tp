package seedu.application.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.application.commons.core.GuiSettings;
import seedu.application.commons.core.LogsCenter;
import seedu.application.commons.util.CollectionUtil;
import seedu.application.model.job.Job;

/**
 * Represents the in-memory model of the application book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ApplicationBook applicationBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Job> filteredJobs;

    /**
     * Initializes a ModelManager with the given applicationBook and userPrefs.
     */
    public ModelManager(ReadOnlyApplicationBook applicationBook, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(applicationBook, userPrefs);

        logger.fine("Initializing with application book: " + applicationBook + " and user prefs " + userPrefs);

        this.applicationBook = new ApplicationBook(applicationBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredJobs = new FilteredList<>(this.applicationBook.getJobList());
    }

    public ModelManager() {
        this(new ApplicationBook(), new UserPrefs());
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
    public Path getApplicationBookFilePath() {
        return userPrefs.getApplicationBookFilePath();
    }

    @Override
    public void setApplicationBookFilePath(Path applicationBookFilePath) {
        requireNonNull(applicationBookFilePath);
        userPrefs.setApplicationBookFilePath(applicationBookFilePath);
    }

    //=========== ApplicationBook ================================================================================

    @Override
    public void setApplicationBook(ReadOnlyApplicationBook applicationBook) {
        this.applicationBook.resetData(applicationBook);
    }

    @Override
    public ReadOnlyApplicationBook getApplicationBook() {
        return applicationBook;
    }

    @Override
    public boolean hasJob(Job job) {
        requireNonNull(job);
        return applicationBook.hasJob(job);
    }

    @Override
    public void deleteJob(Job target) {
        applicationBook.removeJob(target);
    }

    @Override
    public void addJob(Job job) {
        applicationBook.addJob(job);
        updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);
    }

    @Override
    public void setJob(Job target, Job editedJob) {
        CollectionUtil.requireAllNonNull(target, editedJob);

        applicationBook.setJob(target, editedJob);
    }

    //=========== Filtered Job List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Job} backed by the internal list of
     * {@code versionedApplicationBook}
     */
    @Override
    public ObservableList<Job> getFilteredJobList() {
        return filteredJobs;
    }

    @Override
    public void updateFilteredJobList(Predicate<Job> predicate) {
        requireNonNull(predicate);
        filteredJobs.setPredicate(predicate);
    }

    //=========== Sorted Job List Accessors =============================================================

    /**
     * Restores the jobs in {@code applicationBook} to the original unsorted order.
     */
    public void unsortJobs() {
        applicationBook.unsortJobs();
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
        return applicationBook.equals(otherModelManager.applicationBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredJobs.equals(otherModelManager.filteredJobs);
    }

}
