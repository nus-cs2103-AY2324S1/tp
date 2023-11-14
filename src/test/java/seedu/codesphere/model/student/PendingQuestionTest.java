//@@author tiongjjyi
package seedu.codesphere.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PendingQuestionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PendingQuestion(null));
    }

    @Test
    public void toString_test() {
        PendingQuestion pendingQuestion1 = new PendingQuestion("Pending Question 1");
        PendingQuestion pendingQuestion2 = new PendingQuestion("Pending Question 1");
        assertEquals(pendingQuestion1.toString(), pendingQuestion2.toString());
    }
    @Test
    public void equals() {
        PendingQuestion pendingQuestion = new PendingQuestion("Valid Pending Question");

        // same values -> returns true
        assertTrue(pendingQuestion.equals(new PendingQuestion("Valid Pending Question")));

        // same values different capitalisation -> returns false
        assertFalse(pendingQuestion.equals(new PendingQuestion("valid Pending Question")));

        // same object -> returns true
        assertTrue(pendingQuestion.equals(pendingQuestion));

        // null -> returns false
        assertFalse(pendingQuestion.equals(null));

        // different types -> returns false
        assertFalse(pendingQuestion.equals(5.0f));

        // different values -> returns false
        assertFalse(pendingQuestion.equals(new Name("Other Valid Pending Question")));
    }

}
