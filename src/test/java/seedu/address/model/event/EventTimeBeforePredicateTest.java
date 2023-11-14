package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeInstance;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.EventBuilder;


public class EventTimeBeforePredicateTest {
    @Test
    public void testConstructor_validDateTimeString_success() {
        String eventTimeString = "01-01-2024 10:00";
        LocalDateTime eventTime = null;
        try {
            eventTime = parseDateTimeInstance(eventTimeString);
        } catch (ParseException e) {
            fail();
        }
        EventTimeBeforePredicate predicate = new EventTimeBeforePredicate(eventTime);
        assertNotNull(predicate);
    }

    @Test
    public void testConstructor_invalidDateTimeString_throwsException() {
        String invalidEventTimeString = "InvalidDateTime";
        assertThrows(ParseException.class, () -> new EventTimeBeforePredicate(
                parseDateTimeInstance(invalidEventTimeString)));
    }

    @Test
    public void testTest_eventTimeBeforePredicate_returnsTrue() throws ParseException {
        Event event = new EventBuilder().withDuration("01-01-2024 10:00", "01-01-2024 11:00").build();
        EventTimeBeforePredicate predicate = null;
        try {
            predicate = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2024 11:00"));
        } catch (ParseException e) {
            fail();
        }
        assertTrue(predicate.test(event));
    }

    @Test
    public void testTest_eventTimeEqualOrAfterPredicate_returnsFalse() throws ParseException {
        Event event = new EventBuilder().withDuration("01-01-2024 10:00", "01-01-2024 11:00").build();
        EventTimeBeforePredicate predicate = null;
        try {
            predicate = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2024 10:00"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertFalse(predicate.test(event));
    }

    @Test
    public void testEquals_equalPredicates_returnsTrue() {
        EventTimeBeforePredicate predicate1 = null;
        EventTimeBeforePredicate predicate2 = null;
        try {
            predicate1 = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2024 10:00"));
            predicate2 = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2024 10:00"));
        } catch (ParseException e) {
            fail();
        }
        assertEquals(predicate1, predicate2);
    }

    @Test
    public void testEquals_unequalPredicates_returnsFalse() {
        EventTimeBeforePredicate predicate1 = null;
        EventTimeBeforePredicate predicate2 = null;
        try {
            predicate1 = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2024 10:00"));
            predicate2 = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2024 11:00"));
        } catch (ParseException e) {
            fail();
        }
        assertNotEquals(predicate1, predicate2);
    }

    @Test
    public void testEquals_sameObject_returnsTrue() {
        EventTimeBeforePredicate predicate = new EventTimeBeforePredicate(LocalDateTime.now());
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void testEquals_nullObject_returnsFalse() {
        EventTimeBeforePredicate predicate = new EventTimeBeforePredicate(LocalDateTime.now());
        assertFalse(predicate.equals(null));
    }

    @Test
    public void testEquals_differentClass_returnsFalse() {
        EventTimeBeforePredicate predicate = new EventTimeBeforePredicate(LocalDateTime.now());
        assertNotEquals(predicate, "NotAPredicate");
    }

    @Test
    public void testEquals_equalEventTimeDifferentObject_returnsTrue() {
        LocalDateTime eventTime = null;
        try {
            eventTime = parseDateTimeInstance("01-01-2024 10:00");
        } catch (ParseException e) {
            fail();
        }
        EventTimeBeforePredicate predicate1 = new EventTimeBeforePredicate(eventTime);
        EventTimeBeforePredicate predicate2 = new EventTimeBeforePredicate(eventTime);
        assertEquals(predicate1, predicate2);
        assertNotSame(predicate1, predicate2);
    }

    @Test
    public void testToString_nullEventTime_returnsExpectedString() {
        EventTimeBeforePredicate predicate = new EventTimeBeforePredicate(null);
        String expectedString = EventTimeBeforePredicate.class.getCanonicalName() + "{event time=null}";
        assertEquals(expectedString, predicate.toString());
    }

    @Test
    public void testToString_validEventTime_returnsExpectedString() {
        EventTimeBeforePredicate predicate = null;
        try {
            predicate = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2024 10:00"));
        } catch (ParseException e) {
            fail();
        }
        String expectedString = EventTimeBeforePredicate.class.getCanonicalName() + "{event time=2024-01-01T10:00}";
        assertEquals(expectedString, predicate.toString());
    }
}
