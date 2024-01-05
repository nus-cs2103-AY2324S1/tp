package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

/**
 * Wraps all data at the task-list level
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class TaskListBook implements ReadOnlyTaskList {

    private final TaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new TaskList();
    }

    public TaskListBook() {}

    /**
     * Creates an TaskList using the Tasks in the {@code toBeCopied}
     */
    public TaskListBook(ReadOnlyTaskList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code TaskList} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskList newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    // task-level operations

    /**
     * Returns the task at the given index.
     */
    public Task getTask(int index) {
        return tasks.getTask(index);
    }

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task list.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the task list.
     * The task must not already exist in the task list.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task list.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.editTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code TaskListBook}.
     * {@code key} must exist in the task list book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tasks", tasks)
                .toString();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskListBook)) {
            return false;
        }

        TaskListBook otherTaskList = (TaskListBook) other;
        return tasks.equals(otherTaskList.tasks);
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
