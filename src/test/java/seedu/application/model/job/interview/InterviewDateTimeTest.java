package seedu.application.model.job.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterviewDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewDateTime(null));
    }

    @Test
    public void constructor_invalidInterviewDateTime_throwsIllegalArgumentException() {
        String invalidInterviewDateTime = "Aug 18 2003";
        assertThrows(IllegalArgumentException.class, () -> new InterviewDateTime(invalidInterviewDateTime));
    }

    @Test
    public void isValidInterviewDateTime() {
        // null interview DateTime
        assertThrows(NullPointerException.class, () -> InterviewDateTime.isValidInterviewDateTime(null));

        // invalid interview DateTime
        assertFalse(InterviewDateTime.isValidInterviewDateTime("")); // empty string
        assertFalse(InterviewDateTime.isValidInterviewDateTime(" ")); // spaces only
        assertFalse(InterviewDateTime.isValidInterviewDateTime("UNCONFIRMED")); // characters only
        assertFalse(InterviewDateTime.isValidInterviewDateTime("18 Aug 2003 1200")); // wrong format

        // valid interview DateTime
        assertTrue(InterviewDateTime.isValidInterviewDateTime("Nov 12 2010 1200")); // before current date
        assertTrue(InterviewDateTime.isValidInterviewDateTime("Nov 12 2030 1200")); // after current date
    }

    @Test
    public void testEqualsAndHashcode() {
        InterviewDateTime interviewDateTime = new InterviewDateTime("Nov 12 2030 1200");

        // same values -> returns true
        assertTrue(interviewDateTime.equals(new InterviewDateTime("Nov 12 2030 1200")));
        assertEquals(interviewDateTime.hashCode(), new InterviewDateTime("Nov 12 2030 1200").hashCode());

        // same object -> returns true
        assertTrue(interviewDateTime.equals(interviewDateTime));

        // null -> returns false
        assertFalse(interviewDateTime.equals(null));

        // different DateTimes -> returns false
        assertFalse(interviewDateTime.equals(5.0f));

        // different values -> returns false
        assertFalse(interviewDateTime.equals(new InterviewDateTime("Dec 12 2030 1200")));
        assertNotEquals(interviewDateTime.hashCode(), new InterviewDateTime("Dec 12 2030 1200").hashCode());
    }
}
