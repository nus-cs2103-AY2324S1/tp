package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void testEqualsAndHashcode() {
        Status status = new Status("TO_BE_SUBMITTED");

        // same values -> returns true
        assertTrue(status.equals(new Status("TO_BE_SUBMITTED")));
        assertEquals(status.hashCode(), new Status("TO_BE_SUBMITTED").hashCode());

        // same object -> returns true
        assertTrue(status.equals(status));

        // null -> returns false
        assertFalse(status.equals(null));

        // different types -> returns false
        assertFalse(status.equals(5.0f));

        // different values -> returns false
        assertFalse(status.equals(new Status("Pending")));
        assertNotEquals(status.hashCode(), new Status("Pending").hashCode());
    }
}
