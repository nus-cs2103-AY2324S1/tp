package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.event.EventPeriod.DATE_TIME_STRING_FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.EventBuilder.DEFAULT_START_TIME_STRING;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.AllDaysEventListManager;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

import java.time.LocalDateTime;

public class CalendarTest {
    private final Calendar calendar = new Calendar();

    @Test
    public void constructor() {
        assertEquals(new AllDaysEventListManager(), calendar.getEventManager());
    }

    @Test
    public void isEmptyValid_emptyCalendar_returnsTrue() {
        Calendar emptyCalendar = new Calendar();
        assertTrue(emptyCalendar.isEmpty());
    }

    @Test
    public void isEmptyValid_nonEmptyCalendar_returnsFalse() {
        this.calendar.clear();
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
    public void deleteEvent_validEvent_successful() {
        Calendar oneEventCalendar = new Calendar();
        oneEventCalendar.addEvent(new EventBuilder().build());
        LocalDateTime expectedDateTime = LocalDateTime.parse(DEFAULT_START_TIME_STRING, DATE_TIME_STRING_FORMATTER);
        oneEventCalendar.deleteEventAt(expectedDateTime);
        assertFalse(oneEventCalendar.hasEvents());
    }

    @Test
    public void deleteEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendar.deleteEventAt(null));
    }

    @Test
    public void findEventAt_validEvent_Successful() {
        Calendar oneEventCalendar = new Calendar();
        Event sample = new EventBuilder().build();
        oneEventCalendar.addEvent(sample);
        LocalDateTime expectedDateTime = LocalDateTime.parse(DEFAULT_START_TIME_STRING, DATE_TIME_STRING_FORMATTER);
        assertEquals(sample, oneEventCalendar.findEventAt(expectedDateTime).get());
    }

    @Test
    public void findEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendar.findEventAt(null));
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


}
