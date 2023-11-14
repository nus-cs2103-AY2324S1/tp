package seedu.address.model.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ApplicationOutcomeTest {
    private final String validOutcome1 = "follow-up";
    private final String validOutcome2 = "accepted";
    private final String validOutcome3 = "offered";
    private final String validOutcome4 = "rejected";
    private final String validOutcome5 = "ghosted";

    private final String invalidOutcome1 = "invalid outcome";

    private final Outcome validOutcomeEnum1 = Outcome.FOLLOW_UP;
    private final Outcome validOutcomeEnum2 = Outcome.ACCEPTED;

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ApplicationOutcome(null));
    }

    @Test
    public void isValidApplicationOutcome() {
        assertTrue(ApplicationOutcome.isValidApplicationOutcome(validOutcome1));
        assertTrue(ApplicationOutcome.isValidApplicationOutcome(validOutcome2));
        assertTrue(ApplicationOutcome.isValidApplicationOutcome(validOutcome3));
        assertTrue(ApplicationOutcome.isValidApplicationOutcome(validOutcome4));
        assertTrue(ApplicationOutcome.isValidApplicationOutcome(validOutcome5));

        assertFalse(ApplicationOutcome.isValidApplicationOutcome(invalidOutcome1));
    }

    @Test
    public void equals() {
        ApplicationOutcome outcome = new ApplicationOutcome(validOutcomeEnum1);

        // same values -> returns true
        assertEquals(outcome, new ApplicationOutcome(validOutcomeEnum1));

        // same object -> returns true
        assertEquals(outcome, outcome);

        // null -> returns false
        assertNotEquals(null, outcome);

        // different types -> returns false
        assertFalse(outcome.equals(5.0f));

        // different values -> returns false
        assertNotEquals(outcome, new ApplicationOutcome(validOutcomeEnum2));
    }
}
