package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterviewTimeTest {
    @Test
    public void constructor_invalidInterviewTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new InterviewTime(invalidTime));
    }

    @Test
    public void isValidInterviewTime() {

        // invalid time
        assertFalse(InterviewTime.isValidTime("")); // empty string
        assertFalse(InterviewTime.isValidTime(" ")); // spaces only
        assertFalse(InterviewTime.isValidTime("^")); // only numbers and '/'
        assertFalse(InterviewTime.isValidTime("10/30/2003 3pm")); // invalid format

        // valid name
        assertTrue(InterviewTime.isValidTime("01/01/2001 0100")); // correct format
        assertTrue(InterviewTime.isValidTime("15/12/2015 1500")); // correct format
        assertTrue(InterviewTime.isValidTime("07/01/2003 1900")); // correct format
        assertTrue(InterviewTime.isValidTime("19/10/1970 2200")); // correct format
        assertTrue(InterviewTime.isValidTime("cancel")); // correct format
    }

    @Test
    public void equals() {
        InterviewTime interviewTime = new InterviewTime("cancel");

        // same values -> returns true
        assertTrue(interviewTime.equals(new InterviewTime("cancel")));

        // same object -> returns true
        assertTrue(interviewTime.equals(interviewTime));

        // null -> returns false
        assertFalse(interviewTime.equals(null));

        // different types -> returns false
        assertFalse(interviewTime.equals(5.0f));

        // different values -> returns false
        assertFalse(interviewTime.equals(new InterviewTime("12/12/2012 1212")));
    }
}
