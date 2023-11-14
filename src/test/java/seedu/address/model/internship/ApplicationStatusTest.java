package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ApplicationStatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ApplicationStatus(null));
    }

    @Test
    public void constructor_invalidApplicationStatus_throwsIllegalArgumentException() {
        String invalidApplicationStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new ApplicationStatus(invalidApplicationStatus));
    }

    @Test
    public void isValidApplicationStatus() {
        // null applicationStatus -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> ApplicationStatus.isValidApplicationStatus(null));

        // invalid applicationStatuses -> returns false
        assertFalse(ApplicationStatus.isValidApplicationStatus("")); // empty string
        assertFalse(ApplicationStatus.isValidApplicationStatus(" ")); // spaces only
        assertFalse(ApplicationStatus.isValidApplicationStatus("Still trying")); // invalid status (not enum value)

        // valid applicationStatuses -> returns true
        assertTrue(ApplicationStatus.isValidApplicationStatus("Yet to apply"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("Applied"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("In progress"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("Accepted"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("Rejected"));
    }

    @Test
    public void equals() {
        ApplicationStatus applicationStatus = new ApplicationStatus("Yet to apply");

        // same values -> returns true
        assertTrue(applicationStatus.equals(new ApplicationStatus("Yet to apply")));

        // same object -> returns true
        assertTrue(applicationStatus.equals(applicationStatus));

        // null -> returns false
        assertFalse(applicationStatus.equals(null));

        // different values -> returns false
        assertFalse(applicationStatus.equals(new ApplicationStatus("Applied")));
        assertFalse(applicationStatus.equals(new ApplicationStatus("Accepted")));
        assertFalse(applicationStatus.equals(new ApplicationStatus("Rejected")));
    }

    @Test
    public void compareTo_sameApplicationStatus_returnsZero() {
        ApplicationStatus status1 = new ApplicationStatus("In progress");
        ApplicationStatus status2 = new ApplicationStatus("In progress");

        assertEquals(0, status1.compareTo(status2));
    }

    @Test
    public void compareTo_status1EarlierThanStatus2_returnsNegative() {
        ApplicationStatus status1 = new ApplicationStatus("Accepted");
        ApplicationStatus status2 = new ApplicationStatus("Rejected");

        assertEquals(true, status1.compareTo(status2) < 0);
    }

    @Test
    public void compareTo_status1LaterThanStatus2_returnsPositive() {
        ApplicationStatus status1 = new ApplicationStatus("Rejected");
        ApplicationStatus status2 = new ApplicationStatus("Accepted");

        assertEquals(true, status1.compareTo(status2) > 0);
    }
}
