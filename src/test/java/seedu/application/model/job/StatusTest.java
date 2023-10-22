package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.model.job.Status.IN_PROGRESS;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null status
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid status
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("^")); // only non-alphanumeric characters
        assertFalse(Status.isValidStatus("peter")); // does not match anything in enum JobStatus

        // valid status
        assertTrue(Status.isValidStatus("to_be_submitted")); // all lowercase
        assertTrue(Status.isValidStatus("Pending")); // first letter capitalised
        assertTrue(Status.isValidStatus("appROVed")); // some lower case, some upper case
        assertTrue(Status.isValidStatus("REJECTED")); // all uppercase
    }

    @Test
    public void equals() {
        Status status = new Status(IN_PROGRESS);

        // same values -> returns true
        assertEquals(status, new Status(IN_PROGRESS));

        // same object -> returns true
        assertEquals(status, status);

        // null -> returns false
        assertNotEquals(null, status);

        // different types -> returns false
        assertNotEquals(5.0f, status);

        // different values -> returns false
        assertNotEquals(status, new Status(Status.JobStatus.PENDING.toString()));
    }
}
