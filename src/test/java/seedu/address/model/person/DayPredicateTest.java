package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;


public class DayPredicateTest {
    @Test
    public void equals() {
        DayPredicate firstPredicate = new DayPredicate(new Day("Monday"));
        DayPredicate secondPredicate = new DayPredicate(new Day("Wednesday"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DayPredicate firstPredicateCopy = new DayPredicate(new Day("Mon"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personWithSameDay_returnsTrue() {
        // Same day input
        DayPredicate predicate = new DayPredicate(new Day("Monday"));
        assertTrue(predicate.test(new PersonBuilder().withDay("Monday").build()));

        // Short-form day input
        predicate = new DayPredicate(new Day("Mon"));
        assertTrue(predicate.test(new PersonBuilder().withDay("Monday").build()));

        // Mixed-case day input
        predicate = new DayPredicate(new Day("MOnDay"));
        assertTrue(predicate.test(new PersonBuilder().withDay("Monday").build()));
    }

    @Test
    public void test_personWithDifferentDay_returnsFalse() {
        // Non-matching day
        DayPredicate predicate = new DayPredicate(new Day("Monday"));
        assertFalse(predicate.test(new PersonBuilder().withDay("Tue").build()));
    }

    @Test
    public void toStringMethod() {
        Day day = new Day("Mon");
        DayPredicate predicate = new DayPredicate(new Day("Monday"));

        String expected = DayPredicate.class.getCanonicalName() + "{day=" + day + "}";
        assertEquals(expected, predicate.toString());
    }
}
