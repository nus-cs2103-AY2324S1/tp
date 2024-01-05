package seedu.address.storage;

import static seedu.address.model.task.Task.FORMATTER;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskPriority;
import seedu.address.model.task.TaskProgress;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    private final String name;
    private final String description;
    private final String priority;
    private final String date;
    private final String progress;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("description") String description,
                           @JsonProperty("priority") String priority, @JsonProperty("date") String date,
                           @JsonProperty("progress") String progress) {
        this.name = name;
        this.description = description;
        this.progress = progress;
        this.priority = priority;
        this.date = date;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().taskName;
        description = source.getDescription().description;
        priority = source.getPriority().name();
        progress = source.getProgress().name();
        date = source.getDate() != null
                ? source.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : "";
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskName.class.getSimpleName()));
        }
        if (!TaskName.isValidName(name)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelName = new TaskName(name);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskDescription.class.getSimpleName()));
        }
        if (!TaskDescription.isValidDescription(description)) {
            throw new IllegalValueException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        final TaskDescription modelDescription = new TaskDescription(description);

        final TaskPriority modelPriority;
        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskPriority.class.getSimpleName()));
        }
        try {
            modelPriority = TaskPriority.valueOf(priority);
        } catch (Exception e) {
            throw new IllegalValueException(TaskPriority.MESSAGE_CONSTRAINTS);
        }

        final LocalDate localDate;
        if (date.equals("")) {
            localDate = null;
        } else {
            localDate = LocalDate.parse(date, FORMATTER);
        }

        final TaskProgress modelProgress;
        if (progress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskProgress.class.getSimpleName()));
        }
        try {
            modelProgress = TaskProgress.valueOf(progress);
        } catch (Exception e) {
            throw new IllegalValueException(TaskProgress.MESSAGE_CONSTRAINTS);
        }



        return new Task(modelName, modelDescription, modelPriority, localDate, modelProgress);
    }

}
