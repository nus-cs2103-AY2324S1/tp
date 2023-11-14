package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.testutil.EventBuilder;

public class EventListTest {

    @Test
    public void contains_eventNotInList_returnsFalse() {
        UniqueEventList eventList = new UniqueEventList();
        Event event = null;
        try {
            event = new EventBuilder().withName("Event 1").build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertFalse(eventList.contains(event));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        UniqueEventList eventList = new UniqueEventList();
        Event event = null;
        try {
            event = new EventBuilder().withName("Event 1").build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        eventList.add(event);
        assertTrue(eventList.contains(event));
    }

    @Test
    public void add_validEvent_returnsTrue() {
        UniqueEventList eventList = new UniqueEventList();
        Event event = null;
        try {
            event = new EventBuilder().withName("Event 1").build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        eventList.add(event);
    }
    @Test
    public void remove_eventNotInList_returnsFalse() throws ParseException {
        UniqueEventList eventList = new UniqueEventList();
        Event event = new EventBuilder().withName("Event 1").build();
        assertThrows(EventNotFoundException.class, () -> eventList.remove(event));
    }

    @Test
    public void remove_eventInList_returnsFalse() throws ParseException {
        UniqueEventList eventList = new UniqueEventList();
        Event event = new EventBuilder().withName("Event 1").build();
        eventList.add(event);
        eventList.remove(event);
        assertFalse(eventList.contains(event));
    }

    @Test
    public void setEvents_validReplacement_success() throws ParseException {
        // Arrange
        UniqueEventList originalList = new UniqueEventList();
        Event event1 = new EventBuilder().build();
        Event event2 = new EventBuilder().withName("Meeting").build();
        originalList.add(event1);

        UniqueEventList replacementList = new UniqueEventList();
        Event event3 = new EventBuilder().withName("Party").build();
        replacementList.add(event2);
        replacementList.add(event3);

        // Act
        originalList.setEvents(replacementList);

        assertTrue(originalList.contains(event2));
        assertTrue(originalList.contains(event3));
    }

    @Test
    public void setEvents_nullReplacement_throwsNullPointerException() {
        // Arrange
        UniqueEventList originalList = new UniqueEventList();
        Event event1 = null;
        try {
            event1 = new EventBuilder().build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        originalList.add(event1);

        // Act and Assert
        assertThrows(NullPointerException.class, () -> originalList.setEvents((UniqueEventList) null));
    }

    @Test
    public void setEvents_emptyReplacement_success() {
        // Arrange
        UniqueEventList originalList = new UniqueEventList();
        Event event1 = null;
        try {
            event1 = new EventBuilder().build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        originalList.add(event1);

        UniqueEventList replacementList = new UniqueEventList(); // Empty list

        // Act
        originalList.setEvents(replacementList);

        // Assert
        assertFalse(originalList.contains(event1));
    }

    @Test
    public void iterator() throws ParseException {
        UniqueEventList eventList = new UniqueEventList();
        Event event1 = new EventBuilder().withName("Event 1").build();
        Event event2 = new EventBuilder().withName("Event 2").build();
        eventList.add(event1);
        eventList.add(event2);
        assertTrue(eventList.iterator().hasNext());
    }

    @Test
    public void equals_sameInstance_true() {
        // Arrange
        UniqueEventList list = new UniqueEventList();

        // Act and Assert
        assertTrue(list.equals(list));
    }

    @Test
    public void equals_differentClasses_false() {
        // Arrange
        UniqueEventList list = new UniqueEventList();
        Object object = new Object();

        // Act and Assert
        assertFalse(list.equals(object));
    }

    @Test
    public void equals_differentInternalLists_false() throws ParseException {
        // Arrange
        UniqueEventList list1 = new UniqueEventList();
        UniqueEventList list2 = new UniqueEventList();
        list1.add(new EventBuilder().build());
        list2.add(new EventBuilder().withName("Meeting").build());

        // Act and Assert
        assertFalse(list1.equals(list2));
    }

    @Test
    public void equals_sameInternalLists_true() throws ParseException {
        // Arrange
        UniqueEventList list1 = new UniqueEventList();
        UniqueEventList list2 = new UniqueEventList();
        Event event1 = new EventBuilder().build();
        list1.add(event1);
        list2.add(event1);

        // Act and Assert
        assertTrue(list1.equals(list2));
    }

    @Test
    public void toString_emptyList_returnsEmptyString() {
        // Arrange
        UniqueEventList list = new UniqueEventList();

        // Act and Assert
        assertEquals("[]", list.toString());
    }

    @Test
    public void toString_nonEmptyList_returnsFormattedString() throws ParseException {
        // Arrange
        UniqueEventList list = new UniqueEventList();
        Event event1 = new EventBuilder().withName("Meeting").build();
        Event event2 = new EventBuilder().withName("Party").build();
        list.add(event1);
        list.add(event2);

        // Act and Assert
        assertEquals(Arrays.asList(event1, event2).toString(), list.toString());
    }
}
