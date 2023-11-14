package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 * A task is considered unique by comparing using {@code Task#equals(Task)}. As such, adding and updating of
 * task uses Task#equals(Task) for equality to ensure that the task being added or updated is
 * unique in terms of identity in the UniqueTaskList. However, the removal of a Task uses Task#equals(Object) to
 * ensure that the task with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 */
public class UniqueTaskList implements Iterable<Task> {
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
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(TaskDescription taskDescription, Name associatedEventName) {
        requireAllNonNull(taskDescription, associatedEventName);
        for (int i = 0; i < internalList.size(); i++) {
            Task curr = internalList.get(i);
            if (curr.getDescription().equals(taskDescription)
                    && curr.getAssociatedEvent().getName().equals(associatedEventName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a task to an event.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Deletes the specified task from the list.
     * The task must already exist in the task list.
     */
    public void delete(Task toDelete) {
        requireNonNull(toDelete);
        if (!contains(toDelete)) {
            throw new TaskNotFoundException();
        }
        internalList.remove(toDelete);
    }

    /**
     * Deletes all the specified task from the list.
     * @param tasksToDelete set of tasks to delete.
     */
    public void deleteTasksFromEvent(Set<Task> tasksToDelete) {
        requireNonNull(tasksToDelete);
        for (Task taskToDelete : tasksToDelete) {
            delete(taskToDelete);
        }
    }

    /**
     * Marks the given task as completed.
     */
    public void mark(Task toMark) {
        requireNonNull(toMark);
        Task marked = new Task(toMark.getDescription(), toMark.getDate(),
                toMark.getAssociatedEvent(), true);
        setTask(toMark, marked);
    }

    /**
     * Marks the given task as not completed.
     */
    public void unmark(Task toUnmark) {
        requireNonNull(toUnmark);
        Task unmarked = new Task(toUnmark.getDescription(), toUnmark.getDate(),
                toUnmark.getAssociatedEvent(), false);
        setTask(toUnmark, unmarked);
    }

    public Task getByValues(TaskDescription description, Date date, Event event) throws TaskNotFoundException {
        for (int i = 0; i < internalList.size(); i++) {
            Task curr = internalList.get(i);
            if (curr.getDescription().equals(description)
                    && curr.getDate().equals(date)
                    && curr.getAssociatedEvent().equals(event)) {
                return curr;
            }
        }
        throw new TaskNotFoundException();
    }

    public Task getByValues(TaskDescription taskDescription, Name associatedEventName) throws TaskNotFoundException {
        for (int i = 0; i < internalList.size(); i++) {
            Task curr = internalList.get(i);
            if (curr.getDescription().equals(taskDescription)
                    && curr.getAssociatedEvent().getName().equals(associatedEventName)) {
                return curr;
            }
        }
        throw new TaskNotFoundException();
    }

    public void setTask(Task targetTask, Task editedTask) {
        requireAllNonNull(targetTask, editedTask);
        int index = internalList.indexOf(targetTask);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!targetTask.equals(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        internalList.set(index, editedTask);
    }

    public void setTasks(UniqueTaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        if (!areTasksUnique(tasks)) {
            throw new DuplicateTaskException();
        }
        internalList.setAll(tasks);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueTaskList)) {
            return false;
        }

        UniqueTaskList otherUniqueTaskList = (UniqueTaskList) other;
        return internalList.equals(otherUniqueTaskList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code Tasks} contains only unique Contacts.
     */
    public boolean areTasksUnique(List<Task> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).equals(tasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
