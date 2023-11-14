package seedu.address.model.group.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


/**
 * The Task class represents a task in the StudentConnect application.
 */
public class Task {

    private String task;
    private TaskStatus status;
    private TaskModule module;
    private String type;
    private String by;

    /**
     * Constructs a Task object with a task description, status, module, and save status.
     *
     * @param task       The description of the task.
     * @param status     The status of the task (complete or incomplete).
     * @param module     The module the task is assigned to (CS2103T or CS2101).
     * @param type       The type of task, either a deadline or a todo.
     * @param by         The date the task must be completed by, empty for a todo.
     */
    public Task(String task, TaskStatus status, TaskModule module, String type, String by) {
        this.task = task;
        this.status = status;
        this.module = module;
        this.type = type;
        this.by = by;

        if (!task.isEmpty()) {
            addTask(this.task);
        }
    }

    /**
     * Empty constructor for Task.
     */
    public Task() {

    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task's status and description.
     */
    @Override
    public String toString() {
        return this.type + " " + status.toString() + " " + this.module + " " + this.task + " " + this.by;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task description to add.
     */
    public void addTask(String task) {
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task.
     */
    public TaskStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the task.
     *
     * @param taskStatus The status to set.
     */
    public void setStatus(TaskStatus taskStatus) {
        this.status = taskStatus;
    }

    /**
     * Gets the module of the task.
     *
     * @return The module the task is assigned to.
     */
    public TaskModule getModule() {
        return module;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Gets the type of the task.
     *
     * @return The task type.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The task deadline.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Parses a date and time string to a LocalDateTime object.
     *
     * @param dateTimeString The date and time string in the format "dd/MM/yyyy HHmm".
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public LocalDateTime parseDateTime(String dateTimeString) {
        // Split the input string into date and time parts
        String[] parts = dateTimeString.split(" ", 2);

        // Check if there are exactly two parts (date and time)
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid date/time format: "
                    + dateTimeString);
        }

        String datePart = parts[0];
        String timePart = parts[1];

        // Define a formatter for the date part, e.g., "dd/MM/yyyy"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Parse the date part into a LocalDate object
        LocalDate date = LocalDate.parse(datePart, dateFormatter);

        // Define a formatter for the time part, e.g., "HHmm"
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        // Parse the time part into a LocalTime object
        LocalTime time = LocalTime.parse(timePart, timeFormatter);

        // Combine the date and time into a LocalDateTime object
        return LocalDateTime.of(date, time);
    }

    /**
     * Gets the type of the task.
     *
     * @return The string representing task type.
     */
    public String getTaskType() {
        // Your logic to determine the task type based on the instance's actual class
        if (this instanceof Todo) {
            return "T";
        } else if (this instanceof Deadline) {
            return "D";
        } else {
            return ""; // Handle unknown task types or add appropriate logic
        }
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark() {
        this.status = TaskStatus.NOT_DONE;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return string representation of deadline, or empty string for todo tasks.
     */
    public String getDeadline() {
        if (Objects.equals(this.getTaskType(), "D")) {
            return this.getDeadline();
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task)) {
            return false;
        }

        Task task = (Task) other;
        return Objects.equals(this.task, task.task)
            && this.status == task.status
            && this.module == task.module
            && Objects.equals(this.type, task.type)
            && Objects.equals(this.by, task.by);
    }


}
