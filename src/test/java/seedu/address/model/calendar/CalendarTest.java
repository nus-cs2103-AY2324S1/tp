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
    public void isEmptyValid_emptyCalendar_returnsTrue() {
        Calendar emptyCalendar = new Calendar();
        assertTrue(emptyCalendar.isEmpty());
    }

    @Test
    public void isEmptyValid_nonEmptyCalendar_returnsFalse() {
        this.calendar.addEvent(new EventBuilder().build());
        assertFalse(this.calendar.isEmpty());
    }

    @Test
    public void isClearValid_emptyCalendar_returnsEmptyCalendar() {
        Calendar emptyCalendar = new Calendar();
        emptyCalendar.clear();
        assertTrue(emptyCalendar.isEmpty());
    }

    @Test
    public void isClearValid_nonEmptyCalendar_returnsEmptyCalendar() {
        Calendar oneEventCalendar = new Calendar();
        oneEventCalendar.addEvent(new EventBuilder().build());
        assertFalse(oneEventCalendar.isEmpty());

        oneEventCalendar.clear();
        assertTrue(oneEventCalendar.isEmpty());
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

    @Test
    public void isEqualsValid_nullValue_returnsFalse() {
        this.calendar.clear();
        assertFalse(this.calendar.equals(null));
    }

    @Test
    public void isEqualsValid_nonCalendarObject_returnFalse() {
        this.calendar.clear();
        assertFalse(this.calendar.equals(new Object()));
    }

    @Test
    public void isEqualsValid_equalCalendarDeclaredObject_returnTrue() {
        this.calendar.clear();
        Object equalCalendar = new Calendar();
        assertTrue(this.calendar.equals(equalCalendar));
    }

    @Test
    public void isEqualsValid_notEqualCalendarDeclaredObject_returnFalse() {
        this.calendar.clear();
        this.calendar.addEvent(new EventBuilder().build());
        Object nonEqualCalendar = new Calendar();
        assertFalse(this.calendar.equals(nonEqualCalendar));
    }

    @Test
    public void isEqualsValid_equalCalendarDeclaredCalendar_returnTrue() {
        this.calendar.clear();
        Calendar equalCalendar = new Calendar();
        assertTrue(this.calendar.equals(equalCalendar));
    }

    @Test
    public void isEqualsValid_notEqualCalendarDeclaredCalendar_returnFalse() {
        this.calendar.clear();
        this.calendar.addEvent(new EventBuilder().build());
        Calendar nonEqualCalendar = new Calendar();
        assertFalse(this.calendar.equals(nonEqualCalendar));
    }

    @Test
    public void isRemoveEventValid_removeFromOneEventCalendar_returnsEmptyCalendar() {
        this.calendar.clear();
        this.calendar.addEvent(new EventBuilder().build());
        int validIndex = this.calendar.getEventTree().size();
        this.calendar.removeEvent(validIndex);
        assertTrue(this.calendar.isEmpty());
    }

    @Test
    public void isEditEventPeriodValid_nullEventPeriod_throwsNullPointerException() {
        this.calendar.clear();
        this.calendar.addEvent(new EventBuilder().build());
        int validIndex = this.calendar.getEventTree().size();
        assertThrows(NullPointerException.class, () -> this.calendar.editEventPeriod(
                validIndex, null));
    }
}
