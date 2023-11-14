package seedu.application.model.job.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterviewTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewType(null));
    }

    @Test
    public void constructor_invalidInterviewType_throwsIllegalArgumentException() {
        String invalidInterviewType = "";
        assertThrows(IllegalArgumentException.class, () -> new InterviewType(invalidInterviewType));
    }

    @Test
    public void isValidInterviewType() {
        // null interview type
        assertThrows(NullPointerException.class, () -> InterviewType.isValidInterviewType(null));

        // invalid interview type
        assertFalse(InterviewType.isValidInterviewType("")); // empty string
        assertFalse(InterviewType.isValidInterviewType(" ")); // spaces only
        assertFalse(InterviewType.isValidInterviewType("UNCONFIRMED")); // not any of the options

        // valid interview type
        assertTrue(InterviewType.isValidInterviewType("Technical"));
        assertTrue(InterviewType.isValidInterviewType("ONLINE")); // all upper case
        assertTrue(InterviewType.isValidInterviewType("group")); // al lower case
        assertTrue(InterviewType.isValidInterviewType("BeHaViourAl")); // mixed upper and lower case
    }

    @Test
    public void testEqualsAndHashcode() {
        InterviewType interviewType = new InterviewType("Technical");

        // same values -> returns true
        assertTrue(interviewType.equals(new InterviewType("Technical")));
        assertEquals(interviewType.hashCode(), new InterviewType("Technical").hashCode());

        // same object -> returns true
        assertTrue(interviewType.equals(interviewType));

        // null -> returns false
        assertFalse(interviewType.equals(null));

        // different types -> returns false
        assertFalse(interviewType.equals(5.0f));

        // different values -> returns false
        assertFalse(interviewType.equals(new InterviewType("Behavioural")));
        assertNotEquals(interviewType.hashCode(), new InterviewType("Behavioural").hashCode());
    }
}
