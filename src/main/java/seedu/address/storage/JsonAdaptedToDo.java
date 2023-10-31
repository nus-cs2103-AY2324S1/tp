package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.task.ToDo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class JsonAdaptedToDo {

    private final String taskName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedToDo(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedToDo(ToDo source) {
        taskName = source.getTaskName();
    }

    @JsonValue
    public String getTaskName() {
        return taskName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public ToDo toModelType() throws IllegalValueException {
        return new ToDo(taskName);
    }
}
