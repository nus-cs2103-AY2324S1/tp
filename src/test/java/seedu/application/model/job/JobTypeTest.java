package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobType(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidJobType = "";
        assertThrows(IllegalArgumentException.class, () -> new JobType(invalidJobType));
    }

    @Test
    public void isValidJobType() {
        // null job type
        assertThrows(NullPointerException.class, () -> JobType.isValidJobType(null));

        // invalid job type
        assertFalse(JobType.isValidJobType("")); // empty string
        assertFalse(JobType.isValidJobType(" ")); // spaces only
        assertFalse(JobType.isValidJobType("^")); // only non-alphanumeric characters
        assertFalse(JobType.isValidJobType("peter")); // does not match anything in enum JobTypes

        // valid job type
        assertTrue(JobType.isValidJobType("full_time")); // all lowercase
        assertTrue(JobType.isValidJobType("Full_Time")); // first letter capitalised
        assertTrue(JobType.isValidJobType("full_TIME")); // some lower case, some upper case
        assertTrue(JobType.isValidJobType("FULL_TIME")); // all uppercase
    }

    @Test
    public void equals() {
        JobType jobType = new JobType("FULL_TIME");

        // same values -> returns true
        assertEquals(jobType, new JobType("FULL_TIME"));

        // same object -> returns true
        assertEquals(jobType, jobType);

        // null -> returns false
        assertNotEquals(null, jobType);

        // different types -> returns false
        assertNotEquals(5.0f, jobType);

        // different values -> returns false
        assertNotEquals(jobType, new JobType("PART_TIME"));
    }
}
