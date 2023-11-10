package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.event.exceptions.EventOverlapException;

public class UniqueEventListTest {
    @Test
    public void test_removeEvent_pass() {
        UniqueEventList list = new UniqueEventList();
        Event event = new Event("n", "00:00", "00:00", "loc", "info");
        list.add(event);
        assertDoesNotThrow(() -> list.remove(event));
        assertEquals(list.asUnmodifiableObservableList().size(), 0);
        assertThrows(EventNotFoundException.class, () ->
                list.remove(new Event("n2", "00:00", "00:00", "loc", "info")));
    }

    @Test
    public void test_addEvent_pass() {
        UniqueEventList list = new UniqueEventList();
        Event event1 = new Event("n", "00:00", "00:00", "loc", "info");
        Event event2 = new Event("n2", "00:10", "00:20", "loc", "info");
        Event event2Duplicate = new Event("n2", "00:10", "00:20", "loc", "info");
        Event event3 = new Event("n3", "00:00", "00:00", "loc", "info");
        Event event4 = new Event("n4", "00:50", "00:55", "loc", "info");
        assertDoesNotThrow(() -> list.add(event1));
        assertDoesNotThrow(() -> list.add(event2));
        assertEquals(list.asUnmodifiableObservableList().size(), 2);
        assertThrows(DuplicateEventException.class, () ->
                list.add(event2Duplicate));
        assertThrows(EventOverlapException.class, () ->
                list.add(event3));
        assertDoesNotThrow(() -> list.add(event4));
        assertEquals(list.asUnmodifiableObservableList().size(), 3);
    }

    @Test
    public void test_setEvents_pass() {
        UniqueEventList list1 = new UniqueEventList();
        Event event1 = new Event("n", "00:00", "00:00", "loc", "info");
        Event event1Duplicate = new Event("n", "00:00", "00:00", "loc", "info");
        Event event2 = new Event("n2", "00:10", "00:15", "loc", "info");
        list1.add(event1);
        list1.add(event2);
        UniqueEventList list2 = new UniqueEventList();
        assertDoesNotThrow(() -> list2.setEvents(list1));
        assertEquals(list2.asUnmodifiableObservableList().size(), 2);
        assertEquals(list2.asUnmodifiableObservableList().get(0), event1);
        assertEquals(list2.asUnmodifiableObservableList().get(1), event2);
        List<Event> eventList = Arrays.asList(new Event[]{event1, event2});
        List<Event> eventListWithDuplicate = Arrays.asList(new Event[]{event1, event1Duplicate});
        UniqueEventList list3 = new UniqueEventList();
        assertDoesNotThrow(() -> list3.setEvents(eventList));
        UniqueEventList list4 = new UniqueEventList();
        assertThrows(DuplicateEventException.class, () -> list4.setEvents(eventListWithDuplicate));
    }

    @Test
    public void test_equals_pass() {
        UniqueEventList list1 = new UniqueEventList();
        Event event1 = new Event("n", "00:00", "00:00", "loc", "info");
        Event event2 = new Event("n2", "00:10", "00:15", "loc", "info");
        Event event3 = new Event("n3", "00:20", "00:25", "loc", "info");
        list1.add(event1);
        list1.add(event2);
        UniqueEventList list2 = new UniqueEventList();
        list2.add(event1);
        list2.add(event2);
        assertTrue(list1.equals(list1));
        assertTrue(list1.equals(list2));
        list2.add(event3);
        assertFalse(list1.equals(list2));
        assertFalse(list1.equals(new Object()));
    }

}
