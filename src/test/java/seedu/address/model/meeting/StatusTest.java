package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor() {
        Status status = new Status(false);
        assertEquals(Boolean.FALSE, status.get());
    }

    @Test
    public void mark_completed_throwsIllegalStateException() {
        Status status = new Status(true);
        assertThrows(IllegalStateException.class, () -> status.mark());
    }

    @Test
    public void equals() {
        Status status = new Status(false);

        // same values -> returns true
        assertTrue(status.equals(new Status(false)));

        // same object -> returns true
        assertTrue(status.equals(status));

        // null -> returns false
        assertFalse(status.equals(null));

        // different types -> returns false
        assertFalse(status.equals(5.0f));

        // different values -> returns false
        assertFalse(status.equals(new Status(true)));
    }
    
}
