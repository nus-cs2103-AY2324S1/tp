package seedu.address.model.task;

import javafx.collections.ObservableList;

import java.util.Comparator;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Represents a task manager that stores and manages tasks
 */
public class TaskManager implements ReadOnlyTaskManager{

    private final TaskList tasks;
    private Comparator<Task> sortingOrder;

    /**
     * Constructs a TaskManager object with an empty task list.
     */
    public TaskManager() {
        this.tasks = new TaskList();
        this.sortingOrder = new Task.TaskDeadlineComparator();
    }

    /**
     * Constructs a TaskManager using the tasks in the {@code toBeCopied}
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

    public void addTask(Task task) {
        requireNonNull(task);
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Sets the sorting order setting to sort by deadline.
     */
    public void setSortDeadline() {
        sortingOrder = new Task.TaskDeadlineComparator();
    }

    /**
     * Sets the sorting order setting to sort by description.
     */
    public void setSortDescription() {
        sortingOrder = new Task.TaskDescriptorComparator();
    }

    /**
     * Sorts tasks in the task list according to the current sorting order.
     */
    public void sort() {
        tasks.sortTasks(sortingOrder);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof TaskManager)) {
            return false;
        }

        TaskManager otherTaskManager = (TaskManager) other;
        boolean tasksMatch = this.tasks.equals(otherTaskManager.tasks);
        boolean sortOrderMatch = this.sortingOrder.equals(otherTaskManager.sortingOrder);
        return tasksMatch && sortOrderMatch;
    }
}
