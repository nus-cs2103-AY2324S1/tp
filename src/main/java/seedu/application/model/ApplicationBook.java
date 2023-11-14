package seedu.application.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.model.job.FieldComparator;
import seedu.application.model.job.Job;
import seedu.application.model.job.UniqueJobList;

/**
 * Wraps all data at the application-book level
 * Duplicates are not allowed (by .isSameJob comparison)
 */
public class ApplicationBook implements ReadOnlyApplicationBook {

    private final UniqueJobList jobs;
    {
        jobs = new UniqueJobList();
    }

    public ApplicationBook() {
    }

    /**
     * Creates an ApplicationBook using the Jobs in the {@code toBeCopied}
     */
    public ApplicationBook(ReadOnlyApplicationBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the job list with {@code jobs}.
     * {@code jobs} must not contain duplicate jobs.
     */
    public void setJobs(List<Job> jobs) {
        this.jobs.setJobs(jobs);
    }

    /**
     * Resets the existing data of this {@code ApplicationBook} with {@code newData}.
     */
    public void resetData(ReadOnlyApplicationBook newData) {
        requireNonNull(newData);
        setJobs(newData.getJobList());
    }

    //// job-level operations

    /**
     * Returns true if a job with the same identity as {@code job} exists in the application book.
     */
    public boolean hasJob(Job job) {
        requireNonNull(job);
        return jobs.contains(job);
    }

    /**
     * Adds a job to the application book.
     * The job must not already exist in the application book.
     */
    public void addJob(Job j) {
        jobs.add(j);
    }

    /**
     * Replaces the given job {@code target} in the list with {@code editedJob}.
     * {@code target} must exist in the application book.
     * The job identity of {@code editedJob} must not be the same as another existing job in the application book.
     */
    public void setJob(Job target, Job editedJob) {
        requireNonNull(editedJob);

        jobs.setJob(target, editedJob);
    }

    /**
     * Removes {@code key} from this {@code ApplicationBook}.
     * {@code key} must exist in the application book.
     */
    public void removeJob(Job key) {
        jobs.remove(key);
    }

    /**
     * Sorts the jobs in the application book based on the comparator provided.
     *
     * @param comparator The comparator used to compare 2 jobs.
     */
    public void sortJobs(FieldComparator comparator) {
        jobs.sortJobs(comparator);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("jobs", jobs)
            .toString();
    }

    @Override
    public ObservableList<Job> getJobList() {
        return jobs.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationBook)) {
            return false;
        }

        ApplicationBook otherApplicationBook = (ApplicationBook) other;
        return jobs.equals(otherApplicationBook.jobs);
    }

    @Override
    public int hashCode() {
        return jobs.hashCode();
    }

}
