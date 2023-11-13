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
    private final UniMateCalendar calendar = new UniMateCalendar();

    @Test
    public void constructor() {
        assertEquals(new AllDaysEventListManager(), calendar.getEventManager());
    }

    @Test
    public void isEmptyValid_emptyCalendar_returnsTrue() {
        UniMateCalendar emptyCalendar = new UniMateCalendar();
        assertTrue(emptyCalendar.isEmpty());
    }

    @Test
    public void isEmptyValid_nonEmptyCalendar_returnsFalse() {
        calendar.clear();
        calendar.addEvent(new EventBuilder().build());
        assertFalse(calendar.isEmpty());
    }

    @Test
    public void isClearValid_emptyCalendar_returnsEmptyCalendar() {
        UniMateCalendar emptyCalendar = new UniMateCalendar();
        emptyCalendar.clear();
        assertTrue(emptyCalendar.isEmpty());
    }

    @Test
    public void isClearValid_nonEmptyCalendar_returnsEmptyCalendar() {
        UniMateCalendar oneEventCalendar = new UniMateCalendar();
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
        calendar.clear();
        Event validEvent = new EventBuilder().build();
        calendar.addEvent(validEvent);
        assertTrue(calendar.contains(validEvent));
    }

    @Test
    public void deleteEvent_validEvent_successful() {
        UniMateCalendar oneEventCalendar = new UniMateCalendar();
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
        UniMateCalendar oneEventCalendar = new UniMateCalendar();
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
        UniMateCalendar oneEventCalendar = new UniMateCalendar();
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
    public void hasEvents_noEvent_false() {
        UniMateCalendar noEventCalendar = new UniMateCalendar();
        assertFalse(noEventCalendar.hasEvents());
    }

    @Test
    public void deleteEventsInRange_twoEvents_successful() {
        UniMateCalendar eventCalendar = new UniMateCalendar();
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
        calendar.clear();
        assertFalse(calendar.equals(null));
    }

    @Test
    public void isEqualsValid_nonCalendarObject_returnFalse() {
        calendar.clear();
        assertFalse(calendar.equals(new Object()));
    }

    @Test
    public void isEqualsValid_equalCalendarDeclaredObject_returnTrue() {
        calendar.clear();
        Object equalCalendar = new UniMateCalendar();
        assertTrue(calendar.equals(equalCalendar));
    }

    @Test
    public void isEqualsValid_notEqualCalendarDeclaredObject_returnFalse() {
        calendar.clear();
        calendar.addEvent(new EventBuilder().build());
        Object nonEqualCalendar = new UniMateCalendar();
        assertFalse(calendar.equals(nonEqualCalendar));
    }

    @Test
    public void isEqualsValid_equalCalendarDeclaredCalendar_returnTrue() {
        calendar.clear();
        UniMateCalendar equalCalendar = new UniMateCalendar();
        assertTrue(calendar.equals(equalCalendar));
    }

    @Test
    public void isEqualsValid_notEqualCalendarDeclaredCalendar_returnFalse() {
        calendar.clear();
        calendar.addEvent(new EventBuilder().build());
        UniMateCalendar nonEqualCalendar = new UniMateCalendar();
        assertFalse(calendar.equals(nonEqualCalendar));
    }

    @Test
    public void isEqualsValid_thisCalendar_returnTrue() {
        assertTrue(calendar.equals(calendar));
    }

    @Test
    public void getEarliestEventStartTimeInCurrentWeekTest() {
        calendar.clear();

        assertTrue(calendar.getEarliestEventStartTimeInCurrentWeek().isEmpty());
    }

    @Test
    public void getLatestEventEndTimeInCurrentWeek() {
        calendar.clear();

        assertTrue(calendar.getLatestEventEndTimeInCurrentWeek().isEmpty());
    }
}
