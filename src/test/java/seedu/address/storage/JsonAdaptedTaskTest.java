package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.tasks.Task;
import seedu.address.testutil.TypicalTasks;

public class JsonAdaptedTaskTest {

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        Task standardTask = TypicalTasks.CS2101_OP1_UPLOAD;
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(standardTask);
        assertEquals(standardTask, jsonAdaptedTask.toModelType());
    }

    @Test
    public void toModelType_nullTaskDescription_throwsIllegalValueException() {
        JsonAdaptedTask adaptedTask = new JsonAdaptedTask(null, "NOT_DONE", "CS2101", "TODO", "24/12/2023");
        String expectedMessage = "Task's description is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, adaptedTask::toModelType);
    }

    // Similar tests for other null fields

    @Test
    public void fromModelType_validTaskDetails_returnsJsonAdaptedTask() throws Exception {
        Task standardTask = TypicalTasks.CS2101_OP1_UPLOAD;
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(standardTask);
        Task modelTask = jsonAdaptedTask.toModelType();

        assertEquals(standardTask.getTask(), modelTask.getTask());
        assertEquals(standardTask.getStatus(), modelTask.getStatus());
        assertEquals(standardTask.getModule(), modelTask.getModule());
        assertEquals(standardTask.getType(), modelTask.getType());
        assertEquals(standardTask.getBy(), modelTask.getBy());
    }
}
