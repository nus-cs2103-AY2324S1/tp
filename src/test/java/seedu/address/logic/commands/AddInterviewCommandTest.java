package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInterviews.STANDARD_INTERVIEW;
import static seedu.address.testutil.TypicalInterviews.STANDARD_INTERVIEW_2;

import org.junit.jupiter.api.Test;

/**
 * Tests the AddInterviewCommand class
 * Adapted from AB3 AddCommandTest
 */
public class AddInterviewCommandTest {

    @Test
    public void constructor_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInterviewCommand(null));
    }

    @Test
    public void equals() {
        AddInterviewCommand addInterview1Cmd = new AddInterviewCommand(STANDARD_INTERVIEW);
        AddInterviewCommand addInterview2Cmd = new AddInterviewCommand(STANDARD_INTERVIEW_2);

        // same object -> returns true
        assertEquals(addInterview1Cmd, addInterview1Cmd);

        // same values -> returns true
        AddInterviewCommand addInterview1Copy = new AddInterviewCommand(STANDARD_INTERVIEW);
        assertEquals(addInterview1Cmd, addInterview1Copy);

        // different types -> returns false
        assertNotEquals(1, addInterview1Cmd);

        // null -> returns false
        assertNotEquals(null, addInterview1Cmd);

        // different interviews -> returns false
        assertNotEquals(addInterview1Cmd, addInterview2Cmd);
    }

}
