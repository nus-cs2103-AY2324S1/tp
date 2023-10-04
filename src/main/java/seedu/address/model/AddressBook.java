package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.job.Job;
import seedu.address.model.job.UniqueJobList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueJobList jobs;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        jobs = new UniqueJobList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Jobs in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
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
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setJobs(newData.getJobList());
    }

    //// person-level operations

    /**
     * Returns true if a job with the same identity as {@code job} exists in the address book.
     */
    public boolean hasJob(Job job) {
        requireNonNull(job);
        return jobs.contains(job);
    }

    /**
     * Adds a job to the address book.
     * The job must not already exist in the address book.
     */
    public void addJob(Job j) {
        jobs.add(j);
    }

    /**
     * Replaces the given job {@code target} in the list with {@code editedJob}.
     * {@code target} must exist in the address book.
     * The job identity of {@code editedJob} must not be the same as another existing job in the address book.
     */
    public void setJob(Job target, Job editedJob) {
        requireNonNull(editedJob);

        jobs.setJob(target, editedJob);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeJob(Job key) {
        jobs.remove(key);
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
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return jobs.equals(otherAddressBook.jobs);
    }

    @Override
    public int hashCode() {
        return jobs.hashCode();
    }

}
