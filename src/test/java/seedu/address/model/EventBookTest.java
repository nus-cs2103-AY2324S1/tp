package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENT_1;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;

public class EventBookTest {

    private final EventBook eventBook = new EventBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eventBook.getEventList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEventBook_replacesData() {
        EventBook newData = getTypicalEventBook();
        eventBook.resetData(newData);
        assertEquals(newData, eventBook);
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventBook.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInEventBook_returnsFalse() {
        assertFalse(eventBook.hasEvent(EVENT_1));
    }

    @Test
    public void hasEvent_eventInEventBook_returnsTrue() {
        eventBook.addEvent(EVENT_1);
        assertTrue(eventBook.hasEvent(EVENT_1));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInEventBook_returnsTrue() {
        eventBook.addEvent(EVENT_1);
        Event event = new Event(EVENT_1.getPerson(), EVENT_1.getDescription(), LocalDateTime.now(), LocalDateTime.MAX);
        assertTrue(eventBook.hasEvent(event));
    }

    @Test
    public void toStringMethod() {
        String expected = EventBook.class.getCanonicalName() + "{events=" + eventBook.getEventList() + "}";
        assertEquals(expected, eventBook.toString());
    }

    /**
     * A stub ReadOnlyEventBook whose events list can violate interface constraints.
     */
    private static class EventBookStub implements ReadOnlyEventBook {
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        EventBookStub(Collection<Event> events) {
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
    }

}
