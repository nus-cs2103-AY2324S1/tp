package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.availability.FreeTime;
import seedu.address.model.availability.TimeInterval;
import seedu.address.model.person.predicates.AvailableTimePredicate;
import seedu.address.testutil.PersonBuilder;

public class AvailableTimePredicateTest {
    private final LocalTime time1 = LocalTime.parse("10:00");
    private final LocalTime time2 = LocalTime.parse("12:00");
    private final LocalTime time3 = LocalTime.parse("14:00");

    @Test
    public void equals() {
        TimeInterval timeInterval1 = new TimeInterval(time1, time2);
        TimeInterval timeInterval2 = new TimeInterval(time1, time3);
        AvailableTimePredicate predicate1 = new AvailableTimePredicate(1, timeInterval1);
        AvailableTimePredicate predicate2 = new AvailableTimePredicate(2, timeInterval2);

        // same object -> returns true
        assertEquals(predicate1, predicate1);

        // same values -> returns true
        AvailableTimePredicate predicate1Copy = new AvailableTimePredicate(1, timeInterval1);
        assertEquals(predicate1, predicate1Copy);

        // different types -> returns false
        assertNotEquals(predicate1, 1);

        // null -> returns false
        assertNotEquals(null, predicate1);

        // different values -> returns false
        assertNotEquals(predicate1, predicate2);

        // different day -> returns false
        AvailableTimePredicate predicate3 = new AvailableTimePredicate(3, timeInterval1);
        assertNotEquals(predicate1, predicate3);
    }

    @Test
    public void test_availableTimeMatches_returnsTrue() {
        TimeInterval timeInterval = new TimeInterval(time1, time2);
        AvailableTimePredicate predicate = new AvailableTimePredicate(1, timeInterval);
        assertTrue(predicate.test(new PersonBuilder().withFreeTime(new FreeTime(time1, time2)).build()));
    }

    @Test
    public void test_availableTimeNoMatch_returnsFalse() {
        TimeInterval timeInterval = new TimeInterval(time1, time2);
        AvailableTimePredicate predicate = new AvailableTimePredicate(1, timeInterval);
        assertFalse(predicate.test(new PersonBuilder().withFreeTime(new FreeTime(time2, time3)).build()));
    }

    @Test
    public void test_availableTimeNull_returnsFalse() {
        TimeInterval timeInterval = new TimeInterval(time1, time2);
        AvailableTimePredicate predicate = new AvailableTimePredicate(1, timeInterval);
        assertFalse(predicate.test(new PersonBuilder().withFreeTime(null).build()));
    }

    @Test
    public void test_dayIntervalNull_returnsFalse() {
        TimeInterval timeInterval = new TimeInterval(time1, time2);
        AvailableTimePredicate predicate = new AvailableTimePredicate(1, timeInterval);
        FreeTime freeTime = new FreeTime(time1, time2);
        FreeTime updatedFreeTime = freeTime.updateAvailabilityForDay(1, null);
        assertFalse(predicate.test(new PersonBuilder().withFreeTime(updatedFreeTime).build()));
    }

    @Test
    public void toStringMethod() {
        TimeInterval timeInterval = new TimeInterval(time1, time2);
        AvailableTimePredicate predicate = new AvailableTimePredicate(1, timeInterval);
        String expected = AvailableTimePredicate.class.getCanonicalName() + "{day=1, free time=" + timeInterval
                + "}";
        assertEquals(expected, predicate.toString());
    }

    @Test
    public void toFilterString() {
        TimeInterval timeInterval = new TimeInterval(time1, time2);
        AvailableTimePredicate predicate = new AvailableTimePredicate(1, timeInterval);
        String expected = "\nday: Mon\nfree time: [10:00-12:00]";
        assertEquals(expected, predicate.toFilterString());
    }
}
