package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.lessons.Task;

/**
 * View of the FullTaskList
 */
public interface ReadOnlyFullTaskList {

    /**
     * Returns an unmodifiable view of the full task list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getFullTaskList();

}
