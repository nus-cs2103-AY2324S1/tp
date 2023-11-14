package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.address.Address;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.task.Task;

public class ContactIsInEventPredicateTest {
    @Test
    public void equals() {

        Event firstEvent = new Event(new Name("Job Fest 2023"), new Date("2023-12-23"),
            new Address("NUS"), new HashSet<Contact>(), new HashSet<Task>());
        Event secondEvent = new Event(new Name("Job Fest 2024"), new Date("2024-12-23"),
            new Address("NUS"), new HashSet<Contact>(), new HashSet<Task>());


        ContactIsInEventPredicate firstPredicate = new ContactIsInEventPredicate(firstEvent);
        ContactIsInEventPredicate secondPredicate = new ContactIsInEventPredicate(secondEvent);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContactIsInEventPredicate firstPredicateCopy = new ContactIsInEventPredicate(firstEvent);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different event -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void toString_testEquals() {

        Event firstEvent = new Event(new Name("Job Fest 2023"), new Date("2023-12-23"),
            new Address("NUS"), new HashSet<Contact>(), new HashSet<Task>());

        ContactIsInEventPredicate firstPredicate = new ContactIsInEventPredicate(firstEvent);
        String expected = new ToStringBuilder(firstPredicate).add("event", firstEvent).toString();
        assertTrue(firstPredicate.toString().equals(expected));
    }
}
