package seedu.address.model.lessons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;



public class TaskTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null));
    }

    @Test
    public void constructor_invalidTask_throwsIllegalArgumentException() {
        String invalidTask = "";
        assertThrows(IllegalArgumentException.class, () -> new Task(invalidTask));
    }

    @Test
    public void isValidTask() {
        // null task
        assertThrows(NullPointerException.class, () -> Task.isValidTask(null));

        // invalid addresses
        assertFalse(Task.isValidTask("")); // empty string
        assertFalse(Task.isValidTask(" ")); // spaces only

        // valid addresses
        assertTrue(Task.isValidTask("Testing, description: #01-355"));
        assertTrue(Task.isValidTask("-")); // one character
        assertTrue(Task.isValidTask("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long description
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TASK_1.isSameTask(TASK_1));

        // null -> returns false
        assertFalse(TASK_1.isSameTask(null));

        // same description -> returns true
        Task editedTask1 = new Task(TASK_1.getDescription());
        assertTrue(TASK_1.isSameTask(editedTask1));

        // description differs in case -> returns false
        Task editedTask2 = new Task(TASK_2.getDescription().toLowerCase());
        assertFalse(TASK_2.isSameTask(editedTask2));

        // desc has trailing spaces -> returns true as description is trimmed
        String descWithTrailingSpaces = TASK_2.getDescription() + " ";
        editedTask2 = new Task(descWithTrailingSpaces);
        assertTrue(TASK_2.isSameTask(editedTask2));
    }

    @Test
    public void equals() {
        Task task = new Task("Valid Task");

        // same values -> returns true
        assertTrue(task.equals(new Task("Valid Task")));

        // same object -> returns true
        assertTrue(task.equals(task));

        // null -> returns false
        assertFalse(task.equals(null));

        // different types -> returns false
        assertFalse(task.equals(5.0f));

        // different values -> returns false
        assertFalse(task.equals(new Task("Other Valid Task")));
    }

    @Test
    public void toStringMethod() {
        String expected = TASK_1.getDescription();
        assertEquals(expected, TASK_1.toString());
    }

    @Test
    public void serializeMethod() {
        String expected = "-" + TASK_1.getDescription();
        assertEquals(expected, TASK_1.serialize());
    }

    @Test
    public void of_task_parsesTask() throws ParseException {
        Task doneTask = new Task("Sample", true);
        String doneTaskString = "+Sample";

        Task parsedDoneTask = Task.deserialize(doneTaskString);
        assertEquals(doneTask, parsedDoneTask);
    }

    @Test
    public void of_task_throwsParseError() {
        String failedTaskString = "Sample";
        assertThrows(IllegalArgumentException.class, () -> Task.deserialize(failedTaskString));
    }
}
