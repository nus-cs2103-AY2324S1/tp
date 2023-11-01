package seedu.address.model.task;

import javafx.collections.ObservableList;

/**
 * A read only version of Task Manager.
 */
public interface ReadOnlyTaskManager {
    /**
     * Generates an unmodifiable view of the task list.
     *
     * @return unmodifiable view of the task list.
     */
    ObservableList<Task> getTaskList();

}
