package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.FED;
import static seedu.address.testutil.TypicalTasks.FEED;
import static seedu.address.testutil.TypicalTasks.WALK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;


public class JsonAdaptedTaskTest {

    @Test
    public void testConstructor_nullDetails_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedTask(null));
    }
    @Test
    public void testConstructor_validDetails() {
        assertDoesNotThrow(() -> new JsonAdaptedTask(FEED));
    }

    @Test
    public void toTaskModel_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask feedTask = new JsonAdaptedTask(FEED);
        assertEquals(FEED, feedTask.toTaskType());
    }

    @Test
    public void toTaskModel_invalidTaskDetails_throwsIllegalValueException() throws Exception {
        JsonAdaptedTask invalidTask = new JsonAdaptedTask(" ", false);
        assertThrows(IllegalValueException.class, invalidTask::toTaskType);
    }

    @Test
    public void testEquals_withSameObject() {
        JsonAdaptedTask feedTask = new JsonAdaptedTask(FEED);
        assertEquals(feedTask, feedTask);
    }

    @Test
    public void testEquals_withNull() {
        JsonAdaptedTask feedTask = new JsonAdaptedTask(FEED);
        assertNotEquals(feedTask, null);
    }

    @Test
    public void testEquals_withDifferentTask() {
        JsonAdaptedTask feedTask = new JsonAdaptedTask(FEED);
        JsonAdaptedTask walkTask = new JsonAdaptedTask(WALK);
        assertNotEquals(feedTask, walkTask);
    }

    @Test
    public void testEquals_withDifferentStatus() {
        JsonAdaptedTask feedTaskNotDone = new JsonAdaptedTask(FEED);
        JsonAdaptedTask feedTaskIsDone = new JsonAdaptedTask(FED);
        assertNotEquals(feedTaskNotDone, feedTaskIsDone);
    }

}
