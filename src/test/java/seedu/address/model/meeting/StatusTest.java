package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor() {
        MeetingStatus status = new MeetingStatus(false);
        assertEquals(Boolean.FALSE, status.isComplete);
    }

    @Test
    public void equals() {
        MeetingStatus status = new MeetingStatus(false);

        // same values -> returns true
        assertTrue(status.equals(new MeetingStatus(false)));

        // same object -> returns true
        assertTrue(status.equals(status));

        // null -> returns false
        assertFalse(status.equals(null));

        // different types -> returns false
        assertFalse(status.equals(5.0f));

        // different values -> returns false
        assertFalse(status.equals(new MeetingStatus(true)));
    }
}
