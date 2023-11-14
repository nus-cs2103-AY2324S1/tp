package seedu.address.testutil;

import seedu.address.model.group.tasks.Task;
import seedu.address.model.group.tasks.TaskModule;
import seedu.address.model.group.tasks.TaskStatus;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_DESCRIPTION = "Submit end of course review";
    public static final TaskStatus DEFAULT_STATUS = TaskStatus.NOT_DONE;
    public static final TaskModule DEFAULT_MODULE = TaskModule.CS2103T;
    public static final String DEFAULT_TYPE = "DEADLINE";
    public static final String DEFAULT_BY = "31/12/2023 2359";

    private String taskDescription;
    private TaskStatus status;
    private TaskModule module;
    private String type;
    private String by;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskDescription = DEFAULT_DESCRIPTION;
        status = DEFAULT_STATUS;
        module = DEFAULT_MODULE;
        type = DEFAULT_TYPE;
        by = DEFAULT_BY;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskDescription = taskToCopy.getTask();
        status = taskToCopy.getStatus();
        module = taskToCopy.getModule();
        type = taskToCopy.getType();
        by = taskToCopy.getBy();
    }

    /**
     * Sets the {@code description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.taskDescription = description;
        return this;
    }

    /**
     * Sets the {@code status} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(TaskStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Sets the {@code module} of the {@code Task} that we are building.
     */
    public TaskBuilder withModule(TaskModule module) {
        this.module = module;
        return this;
    }

    /**
     * Sets the {@code type} of the {@code Task} that we are building.
     */
    public TaskBuilder withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the {@code by} (deadline) of the {@code Task} that we are building.
     */
    public TaskBuilder withBy(String by) {
        this.by = by;
        return this;
    }

    public Task build() {
        return new Task(taskDescription, status, module, type, by);
    }
}
