package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.SampleDataUtil.getTagSet;

import org.junit.jupiter.api.Test;

public class TaskTest {

    private static final Task TEST_TASK = new Task(new Title("Test Task 1"), new Note("Test Note 1"),
            Status.STATUS_DONE, getTagSet("Test1"));
    private static final Task TEST_TASK_SAME = new Task(new Title("Test Task 1"), new Note("Test Note 1"),
            Status.STATUS_DONE, getTagSet("Test1"));
    private static final Task TEST_TASK_TITLE = new Task(new Title("Test Task 1"), new Note("Test Note 2"), getTagSet(
            "Test2"));
    private static final Task TEST_TASK_NOTE = new Task(new Title("Test Task 1"), new Note("Test Note 1"), getTagSet(
            "Test2"));
    private static final Task TEST_TASK_STATUS = new Task(new Title("Test Task 1"), new Note("Test Note 1"),
            Status.STATUS_DONE, getTagSet("Test2"));
    private static final Task TEST_TASK_DIFFERENT = new Task(new Title("Test Task 2"), new Note("Test Note 2"),
            getTagSet("Test2"));


    @Test
    public void isSameTask() {
        // null -> returns false
        assertFalse(TEST_TASK.isSameTask(null));

        // all different -> returns false
        assertFalse(TEST_TASK.isSameTask(TEST_TASK_DIFFERENT));

        // only title same -> returns false
        assertFalse(TEST_TASK.isSameTask(TEST_TASK_TITLE));

        // only title and note same -> returns true
        assertTrue(TEST_TASK.isSameTask(TEST_TASK_NOTE));

        // only title, note and status same -> returns true
        assertTrue(TEST_TASK.isSameTask(TEST_TASK_STATUS));

        // all same values -> returns true
        assertTrue(TEST_TASK.isSameTask(TEST_TASK_SAME));

        // same object -> returns true
        assertTrue(TEST_TASK.isSameTask(TEST_TASK));
    }

    @Test
    public void equals() {
        // null -> returns false
        assertFalse(TEST_TASK.equals(null));

        // different type -> returns false
        assertFalse(TEST_TASK.equals(5.0f));

        // all different values -> returns false
        assertFalse(TEST_TASK.equals(TEST_TASK_DIFFERENT));

        // only title same -> returns false
        assertFalse(TEST_TASK.equals(TEST_TASK_TITLE));

        // only title and note same -> returns false
        assertFalse(TEST_TASK.equals(TEST_TASK_NOTE));

        // only title, note and status same -> returns false
        assertFalse(TEST_TASK.equals(TEST_TASK_STATUS));

        // all same values -> returns true
        assertTrue(TEST_TASK.equals(TEST_TASK_SAME));

        // same object -> returns true
        assertTrue(TEST_TASK.equals(TEST_TASK));
    }

    @Test
    public void toStringMethod() {
        String expected = Task.class.getCanonicalName() + "{title=" + TEST_TASK.getTitle()
                + ", note=" + TEST_TASK.getNote() + ", status=" + TEST_TASK.getStatus()
                + ", tags=" + TEST_TASK.getTags() + "}";
        assertEquals(expected, TEST_TASK.toString());
    }

    @Test
    public void hashCodeTest() {
        // different types -> returns false
        assertNotEquals(TEST_TASK.hashCode(), "Test Task 1 Test Note 1".hashCode());

        // all different values -> returns false
        assertNotEquals(TEST_TASK.hashCode(), TEST_TASK_DIFFERENT.hashCode());

        // only title same -> returns false
        assertNotEquals(TEST_TASK.hashCode(), TEST_TASK_TITLE.hashCode());

        // only title and note same -> returns false
        assertNotEquals(TEST_TASK.hashCode(), TEST_TASK_NOTE.hashCode());

        // only title, note and status same -> returns false
        assertNotEquals(TEST_TASK.hashCode(), TEST_TASK_STATUS.hashCode());

        // all same values -> returns true
        assertEquals(TEST_TASK.hashCode(), TEST_TASK_SAME.hashCode());

        // same object -> returns true
        assertEquals(TEST_TASK.hashCode(), TEST_TASK.hashCode());
    }
}
