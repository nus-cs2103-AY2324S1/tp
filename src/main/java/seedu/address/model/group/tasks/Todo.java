package seedu.address.model.group.tasks;

/**
 * The `Todo` class represents a to-do task, which is a basic type of task with a description.
 * It inherits from the `Task` class and provides specific implementations for to-do tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a new `Todo` task with the given description, status, and module.
     *
     * @param task       The description of the to-do task.
     * @param status     The status of the task (complete or incomplete).
     * @param module     The module the task is assigned to (CS2103T or CS2101).
     */
    public Todo(String task, TaskStatus status, TaskModule module) {
        super(task, status, module, "T", "");

    }

}
