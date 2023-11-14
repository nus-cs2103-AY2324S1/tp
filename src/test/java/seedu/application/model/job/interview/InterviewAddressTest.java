package seedu.application.model.job.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterviewAddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewAddress(null));
    }

    @Test
    public void constructor_invalidInterviewAddress_throwsIllegalArgumentException() {
        String invalidInterviewAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new InterviewAddress(invalidInterviewAddress));
    }

    @Test
    public void isValidInterviewAddress() {
        // null interview Address
        assertThrows(NullPointerException.class, () -> InterviewAddress.isValidInterviewAddress(null));

        // invalid interview Address
        assertFalse(InterviewAddress.isValidInterviewAddress("")); // empty string
        assertFalse(InterviewAddress.isValidInterviewAddress(" ")); // spaces only

        // valid interview Address
        assertTrue(InterviewAddress.isValidInterviewAddress("Home")); // mixed upper and lower case
        assertTrue(InterviewAddress.isValidInterviewAddress("OFFICE")); // all upper case
        assertTrue(InterviewAddress.isValidInterviewAddress("street xyz")); // al lower case
        assertTrue(InterviewAddress.isValidInterviewAddress("Blk 123")); // include numbers
    }

    @Test
    public void testEqualsAndHashcode() {
        InterviewAddress interviewAddress = new InterviewAddress("Home");

        // same values -> returns true
        assertTrue(interviewAddress.equals(new InterviewAddress("Home")));
        assertEquals(interviewAddress.hashCode(), new InterviewAddress("Home").hashCode());

        // same object -> returns true
        assertTrue(interviewAddress.equals(interviewAddress));

        // null -> returns false
        assertFalse(interviewAddress.equals(null));

        // different Addresss -> returns false
        assertFalse(interviewAddress.equals(5.0f));

        // different values -> returns false
        assertFalse(interviewAddress.equals(new InterviewAddress("Office")));
        assertNotEquals(interviewAddress.hashCode(), new InterviewAddress("Office").hashCode());
    }
}
