package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void equals() {
        Status status = new Status(Status.TaskStatus.DONE);

        // same values -> returns true
        assertEquals(status, new Status(Status.TaskStatus.DONE));

        // same object -> returns true
        assertEquals(status, status);

        // null -> returns false
        // assertNotEquals calls the 1st argument's equals method
        assertNotEquals(status, null);

        // different types -> returns false
        assertNotEquals(status, 5.0f);

        // different values -> returns false
        assertNotEquals(status, new Status(Status.TaskStatus.NOT_DONE));
    }

    @Test
    public void toStringMethod() {
        Status doneStatus = new Status(Status.TaskStatus.DONE);
        assertEquals("Done", doneStatus.toString());

        Status notDoneStatus = new Status(Status.TaskStatus.NOT_DONE);
        assertEquals("Not Done", notDoneStatus.toString());
    }

    @Test
    public void hashCodeTest() {
        Status status = new Status(Status.TaskStatus.DONE);

        // same value -> same hashcode
        assertEquals(status.hashCode(), new Status(Status.TaskStatus.DONE).hashCode());

        // different value -> different hashcode
        assertNotEquals(status.hashCode(), new Status(Status.TaskStatus.NOT_DONE).hashCode());

        // different types -> returns false
        assertNotEquals(status.hashCode(), "Done".hashCode());
    }
}
