package seedu.application.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.application.commons.core.GuiSettings;
import seedu.application.model.job.FieldComparator;
import seedu.application.model.job.Job;
import seedu.application.model.job.interview.Interview;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Job> PREDICATE_SHOW_ALL_JOBS = unused -> true;

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
     * Returns the user prefs' application book file path.
     */
    Path getApplicationBookFilePath();

    /**
     * Sets the user prefs' application book file path.
     */
    void setApplicationBookFilePath(Path applicationBookFilePath);

    /**
     * Replaces application book data with the data in {@code applicationBook}.
     */
    void setApplicationBook(ReadOnlyApplicationBook applicationBook);

    /**
     * Returns the ApplicationBook
     */
    ReadOnlyApplicationBook getApplicationBook();

    /**
     * Returns true if a job with the same identity as {@code job} exists in the application book.
     */
    boolean hasJob(Job job);

    /**
     * Deletes the given job.
     * The job must exist in the application book.
     */
    void deleteJob(Job target);

    /**
     * Deletes the given interview.
     * The interview must exist in the application book.
     */
    void deleteInterview(Job job, Interview interview);

    /**
     * Adds the given job.
     * {@code job} must not already exist in the application book.
     */
    void addJob(Job job);

    /**
     * Replaces the given job {@code target} with {@code editedJob}.
     * {@code target} must exist in the application book.
     * The job identity of {@code editedJob} must not be the same as another existing job in the application book.
     */
    void setJob(Job target, Job editedJob);
    /**
     * Replaces the given interview {@code target} with {@code editedInteriew} in {@code job}.
     * {@code target} must exist in the job.
     * The interview identity of {@code editedInterview} must not be the same as another existing interview in the job.
     */
    void setInterview(Job job, Interview target, Interview editedInterview);

    /**
     * Returns an unmodifiable view of the filtered job list
     */
    ObservableList<Job> getFilteredJobList();

    /**
     * Updates the filter of the filtered job list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredJobList(Predicate<Job> predicate);

    /**
     * Sorts the jobs in the application book based on the comparator provided.
     *
     * @param comparator The comparator used to compare 2 jobs.
     */
    public void sortJobs(FieldComparator comparator);
}
