package seedu.address.model.group.tasks;

import java.time.LocalDateTime;

import seedu.address.model.group.exceptions.TaskException;

/**
 * The `Deadline` class represents a task with a specific deadline in the Duke program.
 * It is a subclass of the `Task` class and provides functionality to handle tasks with deadlines.
 */
public class Deadline extends Task {

    private String byStr;
    private LocalDateTime by;

    /**
     * Initializes a new `Deadline` task with the specified description, status, module, and deadline.
     *
     * @param task       The description of the task.
     * @param status     The status of the task (complete or incomplete).
     * @param module     The module the task is assigned to (CS2103T or CS2101).
     * @param by         The deadline of the task in string format (dd/MM/yyyy).
     * @throws TaskException If there is an issue parsing the deadline format.
     */
    public Deadline(String task, TaskStatus status, TaskModule module, String by) throws TaskException {
        super(task, status, module, "D", by);
        try {
            this.by = parseDateTime(by);
            this.byStr = by;
        } catch (Exception e) {
            throw new TaskException("Invalid date format :< Please use dd/MM/yyyy\n");
        }
    }

    @Override
    public String getDeadline() {
        return "(by " + this.byStr + ")";
    }

}
