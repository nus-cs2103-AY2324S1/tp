package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class BirthdayWithinDaysPredicateTest {

    @Test
    public void equals() {
        int firstDays = 1;
        int secondDays = 2;

        BirthdayWithinDaysPredicate firstPredicate = new BirthdayWithinDaysPredicate(firstDays);
        BirthdayWithinDaysPredicate secondPredicate = new BirthdayWithinDaysPredicate(secondDays);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BirthdayWithinDaysPredicate firstPredicateCopy = new BirthdayWithinDaysPredicate(firstDays);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_birthdayWithinDays_returnsTrue() {
        // Birthday is today
        String today = LocalDate.now().toString();
        BirthdayWithinDaysPredicate predicate = new BirthdayWithinDaysPredicate(7);
        assertTrue(predicate.test(new PersonBuilder().withBirthday(today).build()));

        // Birthday is tomorrow
        String tomorrow = LocalDate.now().plusDays(1).toString();
        predicate = new BirthdayWithinDaysPredicate(7);
        assertTrue(predicate.test(new PersonBuilder().withBirthday(tomorrow).build()));

        // Birthday is next year but within Days
        String nextYear = LocalDate.now().minusDays(1).toString();
        predicate = new BirthdayWithinDaysPredicate(365);
        assertTrue(predicate.test(new PersonBuilder().withBirthday(nextYear).build()));
    }

    @Test
    public void test_birthdayNotWithinDays_returnsFalse() {
        // Birthday is before today
        String yesterday = LocalDate.now().minusDays(1).toString();
        BirthdayWithinDaysPredicate predicate = new BirthdayWithinDaysPredicate(364);
        assertFalse(predicate.test(new PersonBuilder().withBirthday(yesterday).build()));

        //Birthday is not within days
        String nextWeek = LocalDate.now().plusWeeks(1).toString();
        BirthdayWithinDaysPredicate predicate2 = new BirthdayWithinDaysPredicate(5);
        assertFalse(predicate2.test(new PersonBuilder().withBirthday(nextWeek).build()));
    }

    @Test
    public void test_birthdayNotPresent_returnsFalse() {
        BirthdayWithinDaysPredicate predicate = new BirthdayWithinDaysPredicate(7);
        assertFalse(predicate.test(new PersonBuilder().build()));
    }

    @Test
    public void test_toString() {
        int days = 1;
        BirthdayWithinDaysPredicate predicate = new BirthdayWithinDaysPredicate(days);

        String expected = BirthdayWithinDaysPredicate.class.getCanonicalName() + "{days=" + days + "}";
        assertEquals(expected, predicate.toString());
    }
}
