package seedu.address.model.group.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks in a group.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given initial tasks.
     *
     * @param initialTasks The initial tasks to populate the TaskList.
     */
    public TaskList(List<Task> initialTasks) {
        tasks = new ArrayList<>(initialTasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param taskIndex The index of the task to delete.
     */
    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
        }
    }

    /**
     * Returns the list of tasks in the task list.
     *
     * @return The list of tasks in the task list.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the task at index in the task list.
     *
     * @param index The index of task to be returned.
     * @return The task at the index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Checks if a task with the specified task type and task description exists in the task list.
     *
     * @param taskType        The type of the task.
     * @param taskDescription The description of the task.
     * @return True if a matching task exists, false otherwise.
     */
    public boolean isTaskInAllTasks(String taskType, String taskDescription) {
        for (Task task : tasks) {
            if (task.getTask().equals(taskDescription) && task.getTaskType().equals(taskType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a list of tasks to the task list.
     *
     * @param tasks The list of tasks to add.
     */
    public void addTasks(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Returns true if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a copy of the list of tasks in the task list.
     *
     * @return A copy of the list of tasks.
     */
    public List<Task> getTaskList() {
        return new ArrayList<>(tasks);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String tempStatus;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getStatus() == TaskStatus.NOT_DONE) {
                tempStatus = "❌";
            } else {
                tempStatus = "✅";
            }
            result.append(tempStatus).append(" ").append(task.getType()).append(" ").append(i + 1).append(".")
                .append(" ").append(task.getModule()).append(" ").append(task.getTask())
                .append(" ").append(task.getBy()).append("\n");

        }
        return String.valueOf(result);
    }
}
