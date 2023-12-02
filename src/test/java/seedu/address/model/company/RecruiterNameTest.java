package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RecruiterNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecruiterName(null));
    }

    @Test
    public void constructor_invalidRecruiterName_throwsIllegalArgumentException() {
        String invalidRecruiterName = "";
        assertThrows(IllegalArgumentException.class, () -> new RecruiterName(invalidRecruiterName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> RecruiterName.isValidName(null));

        // invalid name
        assertFalse(RecruiterName.isValidName("")); // empty string
        assertFalse(RecruiterName.isValidName(" ")); // spaces only
        assertFalse(RecruiterName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(RecruiterName.isValidName("Timmy*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(RecruiterName.isValidName("timmy")); // alphabets only
        assertTrue(RecruiterName.isValidName("12345")); // numbers only
        assertTrue(RecruiterName.isValidName("Tricia1")); // alphanumeric characters
        assertTrue(RecruiterName.isValidName("Timmy Tan")); // with capital letters
        assertTrue(RecruiterName.isValidName("Timmy Tan Wei Ming")); // long names
    }

    @Test
    public void equals() {
        RecruiterName recruiterName = new RecruiterName("Valid Recruiter Name");

        // same values -> returns true
        assertTrue(recruiterName.equals(new RecruiterName("Valid Recruiter Name")));

        // same object -> returns true
        assertTrue(recruiterName.equals(recruiterName));

        // null -> returns false
        assertFalse(recruiterName.equals(null));

        // different types -> returns false
        assertFalse(recruiterName.equals(5.0f));

        // different values -> returns false
        assertFalse(recruiterName.equals(new RecruiterName("Other Valid Recruiter Name")));

        // different case -> returns true
        assertTrue(recruiterName.equals(new RecruiterName("Valid RECRUITER Name")));

        // different case -> returns true
        assertTrue(recruiterName.equals(new RecruiterName("valid recruiter name")));

        recruiterName = new RecruiterName("Valid Name 123");

        // Alphanumerics preserved -> returns true
        assertTrue(recruiterName.equals(new RecruiterName("Valid Name 123")));

        recruiterName = new RecruiterName("Valid Name 2");

        //Does not strip numbers
        assertFalse(recruiterName.equals(new RecruiterName("Valid Name ")));

        assertFalse(recruiterName.equals(new RecruiterName("Valid Name")));
    }
}
