package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ScheduleBuilder;

public class ScheduleIsOnDatePredicateTest {
    final Date date1 = new Date(LocalDate.of(2023, 9, 15));
    final Date date2 = new Date(LocalDate.of(2023, 9, 16));

    @Test
    public void equals() {
        ScheduleIsOnDatePredicate firstPredicate = new ScheduleIsOnDatePredicate(date1);
        ScheduleIsOnDatePredicate secondPredicate = new ScheduleIsOnDatePredicate(date2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ScheduleIsOnDatePredicate firstPredicateCopy =
            new ScheduleIsOnDatePredicate(date1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_scheduleOnDate_returnsTrue() {
        ScheduleIsOnDatePredicate predicate = new ScheduleIsOnDatePredicate(
            new Date(ScheduleBuilder.DEFAULT_START_TIME.toLocalDate()));

        assertTrue(predicate.test(new ScheduleBuilder().build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        ScheduleIsOnDatePredicate predicate = new ScheduleIsOnDatePredicate(
            new Date(LocalDate.of(2000, 1, 1))
        );
        assertFalse(predicate.test(new ScheduleBuilder().build()));
    }

    @Test
    public void toStringMethod() {
        ScheduleIsOnDatePredicate predicate = new ScheduleIsOnDatePredicate(date1);

        String expected = ScheduleIsOnDatePredicate.class.getCanonicalName() + "{date=" + date1 + "}";
        assertEquals(expected, predicate.toString());
    }
}
