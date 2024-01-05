package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> TaskDescription.isValidDescription(null));

        // invalid descriptions
        assertFalse(TaskDescription.isValidDescription("sssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")); // >100 characters

        // valid descriptions
        assertTrue(TaskDescription.isValidDescription("")); // empty string
        assertTrue(TaskDescription.isValidDescription("complete by friday"));
        assertTrue(TaskDescription.isValidDescription("-")); // one character
        assertTrue(TaskDescription.isValidDescription("complete the work and do "
                + "the 5 quizzes and all the projects user guide developer guide")); // long description
    }

    @Test
    public void equals() {
        TaskDescription description = new TaskDescription("Valid Description");

        // same values -> returns true
        assertTrue(description.equals(new TaskDescription("Valid Description")));

        // same object -> returns true
        assertTrue(description.equals(description));

        // null -> returns false
        assertFalse(description.equals(null));

        // different types -> returns false
        assertFalse(description.equals(5.0f));

        // different values -> returns false
        assertFalse(description.equals(new TaskDescription("Other Valid Description")));
    }
}
