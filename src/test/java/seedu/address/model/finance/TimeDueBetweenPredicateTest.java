package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeDuration;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Duration;
import seedu.address.model.event.TimeEnd;
import seedu.address.model.event.TimeStart;
import seedu.address.testutil.CommissionBuilder;
class TimeDueBetweenPredicateTest {

    @Test
    public void testConstructor_validDateTimeStrings_success() {
        String startTimeString = "01-01-2024 10:00";
        String endTimeString = "02-01-2024 10:00";
        Duration duration = null;
        try {
            duration = parseDateTimeDuration(startTimeString, endTimeString);
        } catch (ParseException e) {
            fail();
        }
        TimeDueBetweenPredicate predicate = new TimeDueBetweenPredicate(duration);
        assertNotNull(predicate);
    }

    @Test
    public void testConstructor_invalidDateTimeString_throwsException() {
        String invalidTimeDueString = "InvalidDateTime";
        assertThrows(ParseException.class, () -> new TimeDueBetweenPredicate(
                parseDateTimeDuration(invalidTimeDueString, invalidTimeDueString)));
    }

    @Test
    public void testTest_timeDueBetweenPredicate_returnsTrue() {
        Finance finance = new CommissionBuilder().withTimeDue("01-01-2024 11:00").build();
        TimeDueBetweenPredicate predicate = null;
        try {
            predicate = new TimeDueBetweenPredicate(parseDateTimeDuration("01-01-2024 10:00", "01-01-2024 12:00"));
        } catch (ParseException e) {
            fail();
        }
        assertTrue(predicate.test(finance));
    }

    @Test
    public void testTest_timeDueOutsidePredicate_returnsFalse() {
        Finance finance = new CommissionBuilder().withTimeDue("01-01-2024 11:00").build();
        TimeDueBetweenPredicate beforeRange = null;
        TimeDueBetweenPredicate afterRange = null;
        try {
            beforeRange = new TimeDueBetweenPredicate(parseDateTimeDuration("01-01-2024 08:00", "01-01-2024 09:00"));
            afterRange = new TimeDueBetweenPredicate(parseDateTimeDuration("02-01-2024 10:00", "03-01-2024 10:00"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        // before
        assertFalse(beforeRange.test(finance));
        // after
        assertFalse(afterRange.test(finance));
    }

    @Test
    public void testEquals_equalPredicates_returnsTrue() {
        try {
            TimeDueBetweenPredicate predicate1 = new TimeDueBetweenPredicate(
                    parseDateTimeDuration("01-01-2024 10:00", "01-01-2024 11:00"));
            TimeDueBetweenPredicate predicate2 = new TimeDueBetweenPredicate(
                    parseDateTimeDuration("01-01-2024 10:00", "01-01-2024 11:00"));
            assertEquals(predicate1, predicate2);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void testEquals_unequalPredicates_returnsFalse() {
        try {
            TimeDueBetweenPredicate predicate1 = new TimeDueBetweenPredicate(
                    parseDateTimeDuration("01-01-2024 10:00", "01-01-2024 11:00"));
            TimeDueBetweenPredicate predicate2 = new TimeDueBetweenPredicate(
                    parseDateTimeDuration("01-01-2024 10:00", "01-01-2024 12:00"));
            TimeDueBetweenPredicate predicate3 = new TimeDueBetweenPredicate(
                    parseDateTimeDuration("01-01-2024 09:00", "01-01-2024 11:00"));
            // same start, different end
            assertNotEquals(predicate1, predicate2);
            // different start, same end
            assertNotEquals(predicate1, predicate3);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void testEquals_sameObject_returnsTrue() {
        TimeDueBetweenPredicate predicate = null;
        try {
            predicate = new TimeDueBetweenPredicate(
                    new Duration(new TimeStart("now"), new TimeEnd("in 1 min")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void testEquals_nullObject_returnsFalse() {
        TimeDueBetweenPredicate predicate = null;
        try {
            predicate = new TimeDueBetweenPredicate(
                    new Duration(new TimeStart("now"), new TimeEnd("in 1 min")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertFalse(predicate.equals(null));
    }

    @Test
    public void testEquals_differentClass_returnsFalse() {
        TimeDueBetweenPredicate predicate = null;
        try {
            predicate = new TimeDueBetweenPredicate(
                    new Duration(new TimeStart("now"), new TimeEnd("in 1 min")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertNotEquals("NotAPredicate", predicate);
    }

    @Test
    public void testEquals_equalTimeRangeDifferentObject_returnsTrue() {
        Duration duration = null;
        try {
            duration = parseDateTimeDuration("01-01-2024 10:00", "01-01-2024 11:00");
        } catch (ParseException e) {
            fail();
        }
        TimeDueBetweenPredicate predicate1 = new TimeDueBetweenPredicate(duration);
        TimeDueBetweenPredicate predicate2 = new TimeDueBetweenPredicate(duration);
        assertEquals(predicate1, predicate2);
        assertNotSame(predicate1, predicate2);
    }

    @Test
    public void testToString_validTimeBetween_returnsExpectedString() {
        TimeDueBetweenPredicate predicate = null;
        try {
            predicate = new TimeDueBetweenPredicate(parseDateTimeDuration("01-01-2024 10:00", "01-01-2024 11:00"));
        } catch (ParseException e) {
            fail();
        }
        String expectedString = TimeDueBetweenPredicate.class.getCanonicalName()
                + "{timeStart=01-01-2024 10:00, timeEnd=01-01-2024 11:00}";
        assertEquals(expectedString, predicate.toString());
    }

}

