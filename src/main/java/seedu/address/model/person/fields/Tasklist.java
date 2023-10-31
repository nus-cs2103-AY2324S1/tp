package seedu.address.model.person.fields;

import seedu.address.model.person.exceptions.EmptyTasklistException;
import seedu.address.task.Task;
import seedu.address.task.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tasklist {

    public final ArrayList<Task> taskList;
    static String ADDED_MESSAGE_START = "Got it. I've added this task:";

    /**
     * Constructs a Tasklist from a serialized list of tasks.
     * @param listOfTasks The serialized list of tasks.
     * @throws EmptyTasklistException If the list of tasks is null.
     */
    public Tasklist(String listOfTasks) throws EmptyTasklistException {
        if (listOfTasks == null) {
            throw new EmptyTasklistException();
        } else {
            taskList = new ArrayList<>();
            updateTaskList(listOfTasks);
        }
    }

    /**
     * Default constructor that initializes an empty task list.
     */
    public Tasklist() {
        taskList = new ArrayList<>();
    }

    public Tasklist(Tasklist taskListToCopy) {
        ArrayList<Task> newTask = new ArrayList<>();
        for (int i = 0; i < taskListToCopy.getSize(); i++) {
            newTask.add(taskListToCopy.getTask(i));
        }
        this.taskList = newTask;
    }

    /** Checks if the task list is empty.
     * @return True if the list is empty, otherwise false.
     */
    public boolean checkEmpty() {
        return taskList.size() == 0;
    }

    public void toDoHelper(String[] values) {
        if (values.length == 3) {
            addToDo(new ToDo(values[2]));
            checkStatus(values);
        } else {
            System.out.println("Invalid line! Skipping line...");
        }
    }

    public void checkStatus(String[] values){
        if (Objects.equals(values[1], "1")) {
            this.taskList.get(this.taskList.size() - 1).markAsDone();
        }
    }

    public void updateTaskList(String content) {
        String[] listOfStrings = content.split("\n");

        for (String line : listOfStrings) {
            String[] values = line.split(" \\| ");

            if (values.length != 1) {

                if (Objects.equals(values[0], "T")) {
                    toDoHelper(values);
                } else {
                    System.out.println("Invalid task! Skipping line...");
                }
            } else {
                System.out.println("No value in file");
            }
        }
    }

    public String addToDo(ToDo task) {

        taskList.add(task);
        String addedMessageEnd = "Now you have " + taskList.size() + " tasks in the list.";
        return (ADDED_MESSAGE_START + "\n" + task + "\n" + addedMessageEnd);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        if (checkEmpty()) {
            result.append("List is empty!");
        } else {

            int maxLength = taskList.size();
            for (int i = 0; i < maxLength; i++) {
                result.append(i+1).append(". ").append(taskList.get(i)).append("\n");
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tasklist)) {
            return false;
        }

        Tasklist otherList = (Tasklist) other;
        return taskList.equals(otherList.taskList);
    }

    @Override
    public int hashCode() {
        return taskList.hashCode();
    }


    /**
     * Returns the task list as a List of Strings suitable for file storage.
     * @return List of strings representing tasks for storage.
     */
    public List<String> getTaskAsList() {
        List<String> linesToAppend = new ArrayList<>();
        for (Task t:taskList) {
            linesToAppend.add(t.toFileString());
        }
        return linesToAppend.subList(0, linesToAppend.size());
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to mark as done.
     */
    public String markAsDone(int index) {
        if (index > taskList.size()) {
            return "Out of bounds!";
        }
        return taskList.get(index).markAsDone();
    }

    /**
     * Marks a task as undone.
     * @param index The index of the task to mark as undone.
     */
    public String unMark(int index) {
        return taskList.get(index).markAsUnDone();
    }

    /**
     * Removes a task from the list.
     * @param index The index of the task to remove.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the size of the task list.
     * @return The size of the task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified index.
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public List<String> searchByKeyword(String keyword) {
        List<String> linesToAppend = new ArrayList<>();

        for (Task t:taskList) {
            if (t.searchTaskName(keyword)) {
                linesToAppend.add(t.toString());
            }
        }
        return linesToAppend;
    }

}
