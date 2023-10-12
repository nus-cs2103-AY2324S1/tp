package seedu.staffsnap.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobTitle(null));
    }

    @Test
    public void constructor_invalidJobTitle_throwsIllegalArgumentException() {
        String invalidJobTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new JobTitle(invalidJobTitle));
    }

    @Test
    public void isValidJobTitle() {
        // null jobTitle
        assertThrows(NullPointerException.class, () -> JobTitle.isValidJobTitle(null));

        // invalid jobTitles
        assertFalse(JobTitle.isValidJobTitle("")); // empty string
        assertFalse(JobTitle.isValidJobTitle(" ")); // spaces only

        // valid jobTitles
        assertTrue(JobTitle.isValidJobTitle("Blk 456, Den Road, #01-355"));
        assertTrue(JobTitle.isValidJobTitle("-")); // one character
        assertTrue(JobTitle.isValidJobTitle(
                "Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long jobTitle
    }

    @Test
    public void equals() {
        JobTitle jobTitle = new JobTitle("Valid JobTitle");

        // same values -> returns true
        assertTrue(jobTitle.equals(new JobTitle("Valid JobTitle")));

        // same object -> returns true
        assertTrue(jobTitle.equals(jobTitle));

        // null -> returns false
        assertFalse(jobTitle.equals(null));

        // different types -> returns false
        assertFalse(jobTitle.equals(5.0f));

        // different values -> returns false
        assertFalse(jobTitle.equals(new JobTitle("Other Valid JobTitle")));
    }
}
