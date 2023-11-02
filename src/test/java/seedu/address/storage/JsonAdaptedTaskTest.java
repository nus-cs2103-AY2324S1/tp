package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.HYDRATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.TaskDescription;

public class JsonAdaptedTaskTest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    private static final String INVALID_DESCRIPTION = "";
    private static final String VALID_DESCRIPTION = ASSIGNMENT.getDescriptionString();
    private static final String VALID_DEADLINE = ASSIGNMENT.getDeadlineString();


    @Test
    public void toModelType_validTaskDeadline_returnsEvent() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(ASSIGNMENT);
        assertEquals(ASSIGNMENT, task.toModelType());
    }

    @Test
    public void toModelType_validTaskAbsentDeadline_returnsEvent() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(HYDRATION);
        assertEquals(HYDRATION, task.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_DESCRIPTION, VALID_DEADLINE);
        String expectedMessage = TaskDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DEADLINE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

}
