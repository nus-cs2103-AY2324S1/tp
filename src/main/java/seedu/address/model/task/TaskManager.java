package seedu.address.model.task;

import javafx.collections.ObservableList;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Represents a task manager that stores and manages tasks
 */
public class TaskManager implements ReadOnlyTaskManager{

    private final TaskList tasks;

    /**
     * Constructs a TaskManager object with an empty task list.
     */
    public TaskManager() {
        this.tasks = new TaskList();
    }

    /**
     * Creates a TaskManager using the tasks in the {@code toBeCopied}
     */
    public TaskManager(ReadOnlyTaskManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code TaskManager} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskManager newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableList();
    }

    /**
     * Replaces the contents of the taskList with {@code taskList}.
     * {@code taskList} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> taskList) {
        requireNonNull(taskList);

        tasks.setTasks(taskList);
    }

}
