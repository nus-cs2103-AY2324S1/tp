package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.task.exceptions.InvalidSortingOrderException;

/**
 * Represents a task manager that stores and manages tasks
 */
public class TaskManager implements ReadOnlyTaskManager {
    private static final String COMPARATOR_TYPE_DESCRIPTION = "Description";
    private static final String COMPARATOR_TYPE_DEADLINE = "Deadline";

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
        requireNonNull(toBeCopied);
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

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task into the taskManager.
     */
    public void addTask(Task task) {
        requireNonNull(task);
        tasks.add(task);
        sort();
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Sets the sorting order setting to sort by deadline.
     */
    private void setSortDeadline() {
        sortingOrder = new Task.TaskDeadlineComparator();
        sort();
    }

    /**
     * Sets the sorting order setting to sort by description.
     */
    private void setSortDescription() {
        sortingOrder = new Task.TaskDescriptorComparator();
        sort();
    }

    /**
     * Returns the sorting order.
     */
    public Comparator<Task> getSortingOrder() {
        return sortingOrder;
    }

    /**
     * Sets the sorting order of the task list to the specified comparator type.
     */
    public void sortTasksBy(String comparatorType) {
        if (comparatorType.equalsIgnoreCase(COMPARATOR_TYPE_DESCRIPTION)) {
            setSortDescription();
        } else if (comparatorType.equalsIgnoreCase(COMPARATOR_TYPE_DEADLINE)) {
            setSortDeadline();;
        } else {
            throw new InvalidSortingOrderException();
        }
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
