package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.EVENT_1;
import static seedu.address.testutil.TypicalEvents.EVENT_2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class EventTest {

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(EVENT_1.isSameEvent(EVENT_1));

        // null -> returns false
        assertFalse(EVENT_1.isSameEvent(null));

        // same name, all other attributes different -> returns true
        Event editedEvent = new Event(EVENT_1.getPerson(), EVENT_1.getDescription(), LocalDateTime.MIN,
                LocalDateTime.MAX);
        assertTrue(EVENT_1.isSameEvent(editedEvent));

        // different name, all other attributes same -> returns false
        editedEvent = new Event(EVENT_2.getPerson(), EVENT_2.getDescription(), LocalDateTime.MIN, LocalDateTime.MAX);
        assertFalse(EVENT_1.isSameEvent(editedEvent));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event eventCopy = new Event(EVENT_1.getPerson(), EVENT_1.getDescription(), LocalDateTime.MIN,
                LocalDateTime.MAX);
        assertTrue(EVENT_1.equals(eventCopy));

        // same object -> returns true
        assertTrue(EVENT_1.equals(EVENT_1));

        // null -> returns false
        assertFalse(EVENT_1.equals(null));

        // different type -> returns false
        assertFalse(EVENT_1.equals(5));

        // different person -> returns false
        assertFalse(EVENT_1.equals(EVENT_2));

        // different name -> returns false
        Event editedEvent = new Event(EVENT_1.getPerson(), EVENT_2.getDescription(), LocalDateTime.MIN,
                LocalDateTime.MAX);
        assertFalse(EVENT_1.equals(editedEvent));

    }

    @Test
    public void toStringMethod() {
        String expected = Event.class.getSimpleName() + "{name=" + EVENT_1.getPerson().getName()
                + ", description=" + EVENT_1.getDescription() + ", startTime="
                + EVENT_1.getStart_time().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                + ", endTime=" + EVENT_1.getEnd_time().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "}";
        assertEquals(expected, EVENT_1.toString());
    }
}
