package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventPeriod;
import seedu.address.testutil.EventBuilder;

public class CalendarTest {
    private final Calendar calendar = new Calendar();

    @Test
    public void constructor() {
        assertEquals(new TreeMap<EventPeriod, Event>(), calendar.getEventTree());
    }

    @Test
    public void addEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendar.addEvent(null));
    }

    @Test
    public void addEvent_validEvent_successful() {
        this.calendar.clear();
        Event validEvent = new EventBuilder().build();
        this.calendar.addEvent(validEvent);
        assertTrue(this.calendar.contains(validEvent));
    }

    @Test
    public void isEventAddValid_conflictingEvent_returnsFalse() {
        this.calendar.clear();
        Event conflictingEvent = new EventBuilder().build();
        this.calendar.addEvent(conflictingEvent);
        assertFalse(this.calendar.isEventAddValid(conflictingEvent));
    }

    @Test
    public void isEventAddValid_validEvent_returnsTrue() {
        this.calendar.clear();
        Event validEvent = new EventBuilder().build();
        assertTrue(this.calendar.isEventAddValid(validEvent));
    }
}
