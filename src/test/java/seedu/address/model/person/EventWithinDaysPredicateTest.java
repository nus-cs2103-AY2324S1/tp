package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.MeetingBuilder;

class EventWithinDaysPredicateTest {

    @Test
    public void equals() {
        int firstDays = 1;
        int secondDays = 2;

        EventWithinDaysPredicate firstPredicate = new EventWithinDaysPredicate(firstDays);
        EventWithinDaysPredicate secondPredicate = new EventWithinDaysPredicate(secondDays);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventWithinDaysPredicate firstPredicateCopy = new EventWithinDaysPredicate(firstDays);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_eventWithinDays_returnsTrue() throws ParseException {
        //Event is today
        String today = LocalDate.now().toString();
        EventWithinDaysPredicate predicate = new EventWithinDaysPredicate(7);
        assertTrue(predicate.test(new MeetingBuilder().withEventDate(today).build()));

        //Event is tomorrow
        String tomorrow = LocalDate.now().plusDays(1).toString();
        predicate = new EventWithinDaysPredicate(7);
        assertTrue(predicate.test(new MeetingBuilder().withEventDate(tomorrow).build()));

        //Event is next year but within Days
        String nextYear = LocalDate.now().plusYears(1).toString();
        predicate = new EventWithinDaysPredicate(366);
        assertTrue(predicate.test(new MeetingBuilder().withEventDate(nextYear).build()));
    }

    @Test
    public void test_eventNotWithinDays_returnsFalse() throws ParseException {
        // Event is not within days
        String expired = LocalDate.now().plusDays(7).toString();
        EventWithinDaysPredicate predicate = new EventWithinDaysPredicate(1);
        assertFalse(predicate.test(new MeetingBuilder().withEventDate(expired).build()));
    }

    @Test
    public void test_toString() {
        int days = 1;
        EventWithinDaysPredicate predicate = new EventWithinDaysPredicate(days);

        String expected = EventWithinDaysPredicate.class.getCanonicalName() + "{days=" + days + "}";
        assertEquals(expected, predicate.toString());
    }
}
