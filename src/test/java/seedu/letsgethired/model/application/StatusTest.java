package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.testutil.Assert.assertThrows;

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

        // invalid statuses
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("Ghosted")); // irrelevant strings

        // valid statuses
        assertTrue(Status.isValidStatus("Pending"));
        assertTrue(Status.isValidStatus("Accepted"));
        assertTrue(Status.isValidStatus("Interview"));
        assertTrue(Status.isValidStatus("Assessment"));
        assertTrue(Status.isValidStatus("Offered"));
        assertTrue(Status.isValidStatus("Rejected"));
        assertTrue(Status.isValidStatus("REJECTED")); // capitalised
    }

    @Test
    public void equals() {
        Status status = new Status("Pending");

        // same values -> returns true
        assertTrue(status.equals(new Status("Pending")));

        // same object -> returns true
        assertTrue(status.equals(status));

        // null -> returns false
        assertFalse(status.equals(null));

        // different types -> returns false
        assertFalse(status.equals(5.0f));

        // different values -> returns false
        assertFalse(status.equals(new Status("Accepted")));
    }

    @Test
    public void compareTo() {
        Status status1 = new Status("Pending");
        Status status2 = new Status("Accepted");
        Status status3 = new Status("Accepted");

        // status2 < status1
        assertTrue(status2.compareTo(status1) < 0);

        // status2 = status3
        assertEquals(0, status2.compareTo(status3));

        // status1 > status2
        assertTrue(status1.compareTo(status2) > 0);
    }
}
