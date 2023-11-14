package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.EVENT1;
import static seedu.address.testutil.TypicalEvents.EVENT2;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;

public class EventTest {
    @Test
    public void constructor_noName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Event(new EventName(""),
                new Duration(new TimeStart(LocalDateTime.now()), new TimeEnd(LocalDateTime.now())), new HashSet<>(),
                new Location(""), new EventDescription("")));
    }

    @Test
    public void constructor_nullDuration_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(new EventName("Sample Event"), null,
                new HashSet<>(), new Location(""), new EventDescription("")));
    }

    @Test
    public void constructor_validParameters_createsEvent() throws ParseException {
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 13, 0);
        Event event = new Event(new EventName("Sample Event"),
                new Duration(new TimeStart(startTime), new TimeEnd(endTime)), new HashSet<>(),
                new Location(""), new EventDescription(""));
        assertEquals("Sample Event", event.getEventName().toString());
        assertEquals(new TimeStart(startTime).getValue(), event.getDuration().getTimeStartValue());
        assertEquals(new TimeEnd(endTime).getValue(), event.getDuration().getTimeEndValue());
    }

    @Test
    public void getClients_returnsCopyOfClientsList() throws ParseException {
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 13, 0);
        Set<Person> clients = new HashSet<>();
        clients.add(new PersonBuilder(ALICE).build());
        Event event = new Event(new EventName("Sample Event"),
                new Duration(new TimeStart(startTime), new TimeEnd(endTime)), clients,
                new Location(""), new EventDescription(""));
        Set<Person> eventClients = event.getClients();
        assertEquals(clients, eventClients);
        assertFalse(eventClients == clients); // Ensure it's a copy, not the same list object.
    }

    @Test
    public void equals() throws ParseException {
        // Test with same event name, start time and end time
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 13, 0);
        Event event1 = new Event(new EventName("Sample Event"),
                new Duration(new TimeStart(startTime), new TimeEnd(endTime)), new HashSet<>(),
                new Location(""), new EventDescription(""));
        Event event2 = new Event(new EventName("Sample Event"),
                new Duration(new TimeStart(startTime), new TimeEnd(endTime)), new HashSet<>(),
                new Location(""), new EventDescription(""));
        assertTrue(event1.equals(event1)); // other == this
        assertTrue(event1.equals(event2)); // other is equal to this

        // Test with different event names
        Event event3 = new Event(new EventName("Different Event"),
                new Duration(new TimeStart(startTime), new TimeEnd(endTime)), new HashSet<>(),
                new Location(""), new EventDescription(""));
        assertFalse(event1.equals(event3));

        // Test with different type
        assertFalse(event1.equals(1));
    }

    @Test
    public void equals_clients() throws ParseException {
        // Test with same event name, start time and end time
        Event event1 = new EventBuilder().withClient(ALICE).build();
        Event event2 = new EventBuilder().withClient(new PersonBuilder().withName("Alice Pauline").build()).build();
        assertTrue(event1.equals(event2));

        Event event3 = new EventBuilder().withClient(new PersonBuilder().withName("Alice").build()).build();
        Event event4 = new EventBuilder().withClient(new PersonBuilder().withName("Alice").build())
                .withClient(ALICE).build();
        assertFalse(event1.equals(event3));
        assertFalse(event1.equals(event4));
    }

    @Test
    public void hashCode_sameFields_sameHashCode() throws ParseException {
        Event event1 = EVENT1;
        assertEquals(true, event1.hashCode() == event1.hashCode());
    }

    @Test
    public void hashCode_differentFields_differentHashCode() {
        // Create two instances with different field values
        Event event1 = EVENT1;
        Event event2 = EVENT2;

        // Assert that the hash codes are different
        assertEquals(false, event1.hashCode() == event2.hashCode());
    }

}
