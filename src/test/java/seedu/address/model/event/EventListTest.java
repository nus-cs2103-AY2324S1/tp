package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalMeetings.MEETING_WITHOUT_PERSONS;
import static seedu.address.testutil.TypicalMeetings.TP_MEETING;

import org.junit.jupiter.api.Test;

public class EventListTest {

    private final EventList eventList = new EventList();

    @Test
    public void getEventListTest() {
        assertEquals(eventList.getEventList(), eventList.getEventList());
    }

    @Test
    public void addEventTest() {
        eventList.addEvent(TP_MEETING);
        assertTrue(eventList.getEventList().contains(TP_MEETING));
    }

    @Test
    public void setEventTest() {
        eventList.addEvent(TP_MEETING);
        eventList.setEvent(TP_MEETING, MEETING_WITHOUT_PERSONS);
        assertTrue(eventList.getEventList().contains(MEETING_WITHOUT_PERSONS));
        assertFalse(eventList.getEventList().contains(TP_MEETING));
    }

    @Test
    public void removeTest() {
        eventList.addEvent(TP_MEETING);
        eventList.remove(TP_MEETING);
        assertFalse(eventList.getEventList().contains(TP_MEETING));
    }

    @Test
    public void setEventsTest() {
        EventList newEventList = new EventList();
        newEventList.addEvent(TP_MEETING);
        eventList.setEvents(newEventList.getEventList());
        assertEquals(eventList.getEventList(), newEventList.getEventList());
    }

    @Test
    public void getEventsListTest() {
        assertEquals(eventList.getEventsList(), eventList.getEventsList());
    }
}
