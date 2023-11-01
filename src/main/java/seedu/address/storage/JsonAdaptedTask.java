package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lessons.Task;
import seedu.address.model.tag.Tag;

import static seedu.address.model.util.SerializeUtil.deserialize;

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
        descriptionWithStatus = source.toString();
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
//        if (!Task.isValidTask(descriptionWithStatus)) {
//            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
//        }
//        return new Tag(descriptionWithStatus);
        return deserialize(Task.DEFAULT_TASK, Task::of, descriptionWithStatus);
    }

}
