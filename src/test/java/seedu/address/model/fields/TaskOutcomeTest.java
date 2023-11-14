package seedu.address.model.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskOutcomeTest {
    private final String validOutcome1 = "follow-up";
    private final String validOutcome2 = "offered";
    private final String validOutcome3 = "rejected";
    private final String validOutcome4 = "ghosted";

    private final String invalidOutcome1 = "invalid outcome";
    private final String invalidOutcome2 = "accepted";

    private final Outcome validOutcomeEnum1 = Outcome.FOLLOW_UP;
    private final Outcome validOutcomeEnum2 = Outcome.OFFERED;

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskOutcome(null));
    }

    @Test
    public void isValidTaskOutcome() {
        assertTrue(TaskOutcome.isValidTaskOutcome(validOutcome1));
        assertTrue(TaskOutcome.isValidTaskOutcome(validOutcome3));
        assertTrue(TaskOutcome.isValidTaskOutcome(validOutcome4));

        assertFalse(TaskOutcome.isValidTaskOutcome(invalidOutcome1));
        assertFalse(TaskOutcome.isValidTaskOutcome(invalidOutcome2));
    }

    @Test
    public void equals() {
        TaskOutcome outcome = new TaskOutcome(validOutcomeEnum1);

        // same values -> returns true
        assertEquals(outcome, new TaskOutcome(validOutcomeEnum1));

        // same object -> returns true
        assertEquals(outcome, outcome);

        // null -> returns false
        assertNotEquals(null, outcome);

        // different types -> returns false
        assertFalse(outcome.equals(5.0f));

        // different values -> returns false
        assertNotEquals(outcome, new TaskOutcome(validOutcomeEnum2));
    }
}
