package seedu.address.model.lessons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.util.SerializeUtil.deserialize;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ListEntryField;
import seedu.address.model.lessons.exceptions.DuplicateTaskException;
import seedu.address.model.lessons.exceptions.TaskNotFoundException;
import seedu.address.storage.JsonAdaptedTask;


/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 * A task is considered unique by comparing using {@code Task#isSameTask(Task)}. As such, adding and updating of
 * tasks uses Task#isSameTask(Task) for equality so as to ensure that the Task being added or updated is
 * unique in terms of identity in the TaskList. However, the removal of a Task uses Task#equals(Object) so
 * as to ensure that the Task with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#isSameTask(Task)
 */
public class TaskList extends ListEntryField implements Iterable<Task> {
    public static final TaskList DEFAULT_TASKLIST = new TaskList();
    private final ObservableList<Task> internalTaskList = FXCollections.observableArrayList();

    private final ObservableList<Task> internalUnmodifiableTaskList =
            FXCollections.unmodifiableObservableList(internalTaskList);


    /**
     * Default constructor
     */
    public TaskList() {

    }

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalTaskList.stream().anyMatch(toCheck::isSameTask);
    }
    /**
     * Returns the task that clashes with the given argument.
     * @param toCheck Task to check
     * @return Task that clashes with the given argument.
     */
    public int getTaskClashWith(Task toCheck) {
        requireNonNull(toCheck);
        int index = 0;
        for (int i = 0; i < internalTaskList.size(); i++) {
            if (internalTaskList.get(i).isSameTask(toCheck)) {
                index = i;
                break;
            }
        }
        return index + 1;
    }

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalTaskList.add(toAdd);
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalTaskList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        internalTaskList.set(index, editedTask);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    public String remove(int index) {
        requireNonNull(index);
        Task toRemove = internalTaskList.get(index);
        if (!internalTaskList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
        return toRemove.toString();
    }

    public void setTasks(TaskList replacement) {
        requireNonNull(replacement);
        internalTaskList.setAll(replacement.internalTaskList);
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

        internalTaskList.setAll(tasks);
    }

    /**
     * Parses a Tasklist from a String input.
     *
     * @return A TaskList
     */

    public static TaskList of(List<JsonAdaptedTask> tasks) throws ParseException {
        TaskList taskList = new TaskList();
        for (JsonAdaptedTask taskString : tasks) {
            // parse the task
            Task task = deserialize(Task.DEFAULT_TASK, Task::ofDepreciated, taskString.getTaskName());
            taskList.add(task);
        }
        return taskList;

    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableTaskList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalTaskList.iterator();
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
        return internalTaskList.equals(otherTaskList.internalTaskList);
    }

    @Override
    public int hashCode() {
        return internalTaskList.hashCode();
    }

    /**
     * Returns true if {@code tasks} contains only unique tasks.
     */
    private boolean tasksAreUnique(List<Task> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).isSameTask(tasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the set of tasks in this tasklist.
     * @return
     */
    public ArrayList<Task> getTaskListClone() {
        ArrayList<Task> tasksClone = new ArrayList<>();
        for (Task task : internalTaskList) {
            tasksClone.add(task.clone());
        }
        return tasksClone;
    }

    @Override
    public TaskList clone() {
        TaskList cloned = new TaskList();
        internalTaskList.forEach(task -> {
            cloned.add(new Task(task.getDescription(), task.isDone()));
        });
        return cloned;
    }

    /**
     * Returns the size of the tasklist.
     */
    public int size() {
        return internalTaskList.size();
    }
}
