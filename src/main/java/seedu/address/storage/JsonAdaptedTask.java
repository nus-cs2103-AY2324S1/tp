package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.tasks.Task;
import seedu.address.model.group.tasks.TaskModule;
import seedu.address.model.group.tasks.TaskStatus;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    private final String task;
    private final String status;
    private final String module;
    private final String type;
    private final String by;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("task") String task,
                           @JsonProperty("status") String status,
                           @JsonProperty("module") String module,
                           @JsonProperty("type") String type,
                           @JsonProperty("by") String by) {
        this.task = task;
        this.status = status;
        this.module = module;
        this.type = type;
        this.by = by;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        task = source.getTask();
        status = source.getStatus().toString();
        module = source.getModule().toString();
        type = source.getType();
        by = source.getBy();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (task == null) {
            throw new IllegalValueException("Task's description is missing!");
        }

        if (status == null) {
            throw new IllegalValueException("Task's status is missing!");
        }
        if (!TaskStatus.isValidStatus(status)) {
            throw new IllegalValueException(TaskStatus.MESSAGE_CONSTRAINTS);
        }
        final TaskStatus modelStatus = TaskStatus.valueOf(status);

        if (module == null) {
            throw new IllegalValueException("Task's module is missing!");
        }
        if (!TaskModule.isValidModule(module)) {
            throw new IllegalValueException(TaskModule.MESSAGE_CONSTRAINTS);
        }
        final TaskModule modelModule = TaskModule.valueOf(module);

        if (type == null) {
            throw new IllegalValueException("Task's type is missing!");
        }

        if (by == null) {
            throw new IllegalValueException("Task's deadline is missing!");
        }

        return new Task(task, modelStatus, modelModule, type, by);
    }
}
