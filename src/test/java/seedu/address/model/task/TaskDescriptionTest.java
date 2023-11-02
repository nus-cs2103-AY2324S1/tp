package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TaskBuilder.DEFAULT_DESCRIPTION;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.InvalidTaskDescriptionException;

public class TaskDescriptionTest {

    @Test
    public void constructorTest() {
        assertThrows(NullPointerException.class, () -> new TaskDescription(null));

        assertThrows(InvalidTaskDescriptionException.class, () -> new TaskDescription(""));
    }

    @Test
    public void isValidTest() {
        assertThrows(NullPointerException.class, () -> TaskDescription.isValidDescription(null));

        assertFalse(TaskDescription.isValidDescription(""));

        assertTrue(TaskDescription.isValidDescription("a"));
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("A", new TaskDescription("A").get());
    }

    @Test
    public void equalsTest() {
        TaskDescription validTaskDescription = new TaskDescription(DEFAULT_DESCRIPTION);
        TaskDescription equivalentTaskDescription = new TaskDescription(DEFAULT_DESCRIPTION);
        TaskDescription nonEquivalentTaskDescription = new TaskDescription("A");
        Object nonTaskDescriptionObject = new Object();

        assertTrue(validTaskDescription.equals(validTaskDescription));

        assertTrue(validTaskDescription.equals(equivalentTaskDescription));

        assertFalse(validTaskDescription.equals(nonEquivalentTaskDescription));

        assertFalse(validTaskDescription.equals(nonTaskDescriptionObject));
    }

    @Test
    public void compareToTest() {
        TaskDescription descriptionA = new TaskDescription("A");
        TaskDescription descriptionB = new TaskDescription("B");

        assertEquals(1, descriptionB.compareTo(descriptionA));
        assertEquals(-1, descriptionA.compareTo(descriptionB));
        assertEquals(0, descriptionA.compareTo(descriptionA));
    }
}
