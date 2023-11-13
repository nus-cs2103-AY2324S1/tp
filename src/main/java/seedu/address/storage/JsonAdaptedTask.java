package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.exceptions.InvalidDeadlineException;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String description;
    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description,
                            @JsonProperty("deadline") String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        description = source.getDescriptionString();
        deadline = source.getDeadlineString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            TaskDescription.class.getSimpleName()));
        }
        if (!TaskDescription.isValidDescription(description)) {
            throw new IllegalValueException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        final TaskDescription modelDescription = new TaskDescription(description);

        // Deadlines are expected to be saved in this format yyyy-MM-dd HH:mm or - if there is no deadline
        if (deadline == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            Deadline.class.getSimpleName()));
        }
        //Null argument provided if there is deadline is saved as "-" in Json file
        try {
            Deadline modelDeadline = deadline.equals(Deadline.ABSENT_DEADLINE)
                    ? new Deadline((String) null) : new Deadline(deadline);
            return new Task(modelDescription, modelDeadline);
        } catch (InvalidDeadlineException e) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
    }
}
