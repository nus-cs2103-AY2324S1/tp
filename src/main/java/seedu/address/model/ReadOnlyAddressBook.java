package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.job.Job;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the jobs list.
     * This list will not contain any duplicate jobs.
     */
    ObservableList<Job> getJobList();

}
