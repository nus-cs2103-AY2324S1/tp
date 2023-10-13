package seedu.application.model;

import javafx.collections.ObservableList;
import seedu.application.model.job.Job;

/**
 * Unmodifiable view of an application book
 */
public interface ReadOnlyApplicationBook {

    /**
     * Returns an unmodifiable view of the jobs list.
     * This list will not contain any duplicate jobs.
     */
    ObservableList<Job> getJobList();

}
