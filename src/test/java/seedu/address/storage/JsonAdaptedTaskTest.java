package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK1;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskPriority;
import seedu.address.model.task.TaskProgress;

public class JsonAdaptedTaskTest {
    private static final String INVALID_TASK_NAME = "!!! do cs2120@@@"; // no symbols
    private static final String INVALID_TASK_DESCRIPTION = "sssssssssssssssssssssssssssssssssssss"
            + "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"; // >100 characters
    private static final String INVALID_TASK_PRIORITY = "jason"; // not low, medium or high
    private static final String INVALID_PROGRESS = "jason"; // not not_started, pending or done

    private static final String VALID_NAME = TASK1.getName().toString();
    private static final String VALID_DESCRIPTION = TASK1.getDescription().toString();
    private static final String VALID_PRIORITY = TASK1.getPriority().name();
    private static final String VALID_DATE = TASK1.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private static final String VALID_PROGRESS = TASK1.getProgress().name();

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(TASK1);
        assertEquals(TASK1, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_TASK_NAME, INVALID_TASK_DESCRIPTION,
                        VALID_PRIORITY, VALID_DATE, VALID_PROGRESS);
        String expectedMessage = TaskName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DESCRIPTION,
                VALID_PRIORITY, VALID_DATE, VALID_PROGRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, INVALID_TASK_DESCRIPTION,
                        VALID_PRIORITY, VALID_DATE, VALID_PROGRESS);
        String expectedMessage = TaskDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, null, VALID_PRIORITY,
                VALID_DATE, VALID_PROGRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION,
                INVALID_TASK_PRIORITY, VALID_DATE, VALID_PROGRESS);
        String expectedMessage = TaskPriority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, null,
                VALID_DATE, VALID_PROGRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskPriority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidProgress_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION,
                VALID_PRIORITY, VALID_DATE, INVALID_PROGRESS);
        String expectedMessage = TaskProgress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullProgress_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_PRIORITY,
                VALID_DATE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskProgress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }


}
