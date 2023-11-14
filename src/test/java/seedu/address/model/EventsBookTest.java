package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.testutil.EventBuilder;

public class EventsBookTest {

    @Test
    public void constructor_emptyEventsBook_success() {
        EventsBook eventsBook = new EventsBook();
        assertNotNull(eventsBook);
    }

    @Test
    public void constructor_copyEventsBook_success() throws ParseException {
        EventsBook originalEventsBook = new EventsBook();
        Event event = new EventBuilder().withName("Event 1").build();
        originalEventsBook.addEvent(event);

        EventsBook copyEventsBook = new EventsBook(originalEventsBook);
        assertEquals(originalEventsBook, copyEventsBook);
    }

    @Test
    public void setEvents_validEventsList_success() throws ParseException {
        EventsBook eventsBook = new EventsBook();
        List<Event> events = new ArrayList<>();
        events.add(new EventBuilder().withName("Event 1").build());
        eventsBook.setEvents(events);
        assertEquals(events, eventsBook.getEventList());
    }

    @Test
    public void resetData_validEventsBook_success() throws ParseException {
        EventsBook eventsBook = new EventsBook();
        EventsBook newData = new EventsBook();
        newData.addEvent(new EventBuilder().withName("Event 1").build());

        eventsBook.resetData(newData);
        assertEquals(eventsBook, newData);
    }

    @Test
    public void hasEvent_eventInEventsBook_returnsTrue() throws ParseException {
        EventsBook eventsBook = new EventsBook();
        Event event = new EventBuilder().withName("Event 1").build();
        eventsBook.addEvent(event);

        assertTrue(eventsBook.hasEvent(event));
    }

    @Test
    public void hasEvent_eventNotInEventsBook_returnsFalse() throws ParseException {
        EventsBook eventsBook = new EventsBook();
        Event event = new EventBuilder().withName("Event 1").build();

        assertFalse(eventsBook.hasEvent(event));
    }

    @Test
    public void addEvent_eventNotInEventsBook_success() throws ParseException {
        EventsBook eventsBook = new EventsBook();
        Event event = new EventBuilder().withName("Event 1").build();

        eventsBook.addEvent(event);
        assertTrue(eventsBook.hasEvent(event));
    }

    @Test
    public void removeEvent_eventInEventsBook_success() throws ParseException {
        EventsBook eventsBook = new EventsBook();
        Event event = new EventBuilder().withName("Event 1").build();
        eventsBook.addEvent(event);

        eventsBook.removeEvent(event);
        assertFalse(eventsBook.hasEvent(event));
    }

    @Test
    public void removeEvent_eventNotInEventsBook_throwsException() throws ParseException {
        EventsBook eventsBook = new EventsBook();
        Event event = new EventBuilder().withName("Event 1").build();

        assertThrows(EventNotFoundException.class, () -> eventsBook.removeEvent(event));
    }

    @Test
    public void equals() throws ParseException {
        EventsBook eventsBook1 = new EventsBook();
        eventsBook1.addEvent(new EventBuilder().withName("Event 1").build());
        EventsBook eventsBook2 = new EventsBook();
        eventsBook2.addEvent(new EventBuilder().withName("Event 1").build());

        assertEquals(eventsBook1, eventsBook2);
    }

    @Test
    public void toString_notNull() {
        EventsBook eventsBook = new EventsBook();
        assertNotNull(eventsBook.toString());
    }
}
