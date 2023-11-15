package seedu.address.model.person.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DatedEventTest {
    @Test
    public void isDatedEvent_success() {
        DatedEvent datedEvent = new DatedEvent("badminton", "monday 1200 1400",
                "2023-10-30", true);
        assertTrue(datedEvent.isDatedEvent());
    }

    @Test
    public void getType_success() {
        DatedEvent datedEvent = new DatedEvent("badminton", "monday 1200 1400",
                "2023-10-30", true);
        assertEquals("Event", datedEvent.getType());
    }

    @Test
    public void getStringForReminder_success() {
        DatedEvent datedEvent = new DatedEvent("badminton", "monday 1200 1400",
                "2023-10-30", true);
        assertEquals("badminton Monday 1200 1400", datedEvent.getStringForReminder());
    }

    @Test
    public void equals() {
        DatedEvent datedEvent = new DatedEvent("Valid Dated Event", "monday 1200 1400",
                "2023-10-30", true);

        // same values -> returns true
        assertTrue(datedEvent.equals(new DatedEvent("Valid Dated Event", "monday 1200 1400",
                "2023-10-30", true)));

        // same object -> returns true
        assertTrue(datedEvent.equals(datedEvent));

        // null -> returns false
        assertFalse(datedEvent.equals(null));

        // different types -> returns false
        assertFalse(datedEvent.equals(5.0f));

        // different values -> returns false
        assertFalse(datedEvent.equals(new DatedEvent("Other Valid Name", "monday 1200 1400",
                "2023-10-30", true)));

        assertFalse(datedEvent.equals(new DatedEvent("Valid Cca", "tuesday 1200 1400",
                "2023-10-30", true)));
    }

    @Test
    public void testHashCode() {
        DatedEvent datedEvent = new DatedEvent("Valid Dated Event", "monday 1200 1400",
                "2023-10-30", true);
        assertEquals(datedEvent.hashCode(), new DatedEvent("Valid Dated Event", "monday 1200 1400",
                "2023-10-30", true).hashCode());
    }

}
