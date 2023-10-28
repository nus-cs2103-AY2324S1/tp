package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.event.EventPeriod.DATE_TIME_STRING_FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.EventBuilder.DEFAULT_END_TIME_STRING;
import static seedu.address.testutil.EventBuilder.DEFAULT_START_TIME_STRING;
import static seedu.address.testutil.TypicalEvents.CONFERENCE;
import static seedu.address.testutil.TypicalEvents.TRAINING;
import static seedu.address.testutil.TypicalEvents.WORKSHOP;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.AllDaysEventListManager;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.EventPeriodBuilder;

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
    public void findEventAt_validEvent_successful() {
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
    public void getEventsInRange_nullRange_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendar.getEventsInRange(null));
    }

    @Test
    public void getEventsInRange_oneEvent_successful() {
        Calendar oneEventCalendar = new Calendar();
        Event sample = new EventBuilder().build();
        oneEventCalendar.addEvent(sample);
        EventPeriodBuilder builder = new EventPeriodBuilder();
        builder.changeStartAndEnd(DEFAULT_START_TIME_STRING, DEFAULT_END_TIME_STRING);
        assertEquals(List.of(sample), oneEventCalendar.getEventsInRange(builder.build()));
    }

    @Test
    public void deleteEventsInRange_nullRange_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendar.deleteEventsInRange(null));
    }

    @Test
    public void deleteEventsInRange_twoEvents_successful() {
        Calendar eventCalendar = new Calendar();
        eventCalendar.addEvent(WORKSHOP);
        eventCalendar.addEvent(CONFERENCE);
        eventCalendar.addEvent(TRAINING);
        EventPeriodBuilder builder = new EventPeriodBuilder();
        builder.changeStartAndEnd("2023-11-10 14:00", "2023-11-15 17:00");
        assertTrue(eventCalendar.hasEvents());

        eventCalendar.deleteEventsInRange(builder.build());
        assertTrue(eventCalendar.hasEvents());

        builder = new EventPeriodBuilder();
        builder.changeStartAndEnd("2023-11-15 14:00", "2023-11-20 14:01");
        eventCalendar.deleteEventsInRange(builder.build());
        assertFalse(eventCalendar.hasEvents());
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
