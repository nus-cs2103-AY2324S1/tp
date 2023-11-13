package seedu.address.storage;

import static seedu.address.model.util.SerializeUtil.deserialize;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lessons.Task;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Tag}.
 */
public class JsonAdaptedTask {

    private final String descriptionWithStatus;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTask(String tagName) {
        this.descriptionWithStatus = tagName;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        descriptionWithStatus = source.serialize();
    }

    @JsonValue
    public String getTaskName() {
        return descriptionWithStatus;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted subject.
     */
    public Task toModelType() throws IllegalValueException {
        if (!Task.isValidEncodedTask(descriptionWithStatus)) {
            throw new IllegalValueException(Task.DECODED_CONSTRAINTS);
        }
        return deserialize(Task.DEFAULT_TASK, Task::deserialize, descriptionWithStatus);
    }

}
