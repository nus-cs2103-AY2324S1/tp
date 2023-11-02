package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 */
public class TaskList implements Iterable<Task> {
    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a task to the list.
     * An equivalent task must not already exist in the list.
     */
    public void add(Task task) {
        requireNonNull(task);
        if (contains(task)) {
            throw new DuplicateTaskException();
        }
        internalList.add(task);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Removes a task at specified index from the list and returns the removed task.
     */
    public Task remove(int index) {
        requireNonNull(index);

        return internalList.remove(index);
    }

    public void setTasks(TaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        if (!tasksAreUnique(tasks)) {
            throw new DuplicateTaskException();
        }
        internalList.setAll(tasks);
    }

    /**
     * Sorts tasks in the list according to the current sorting order setting of the list.
     */
    public void sortTasks(Comparator<Task> sortingOrder) {
        internalList.sort(sortingOrder);
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableList() {
        return internalUnmodifiableList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskList)) {
            return false;
        }

        TaskList otherTaskList = (TaskList) other;
        return internalList.equals(otherTaskList.internalList);
    }

    /**
     * Returns true if {@code tasks} contains only unique tasks.
     */
    private boolean tasksAreUnique(List<Task> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).equals(tasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return internalList.toString();
    }


    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }
}
